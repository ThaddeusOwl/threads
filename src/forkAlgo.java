import java.util.concurrent.RecursiveTask;

public class forkAlgo extends RecursiveTask<String> {

    int rowStart;
    int rowEnd;
    int rows;
    int columns;
    float[][] terrain;
    int seqCutOff;


    public forkAlgo(float[][] g, int rs, int re, int r, int c, int sco){
        terrain = g;
        rowStart = rs;
        rowEnd = re;
        rows = r;
        columns = c;
        seqCutOff = sco;
    }

    protected String compute() {
        String basins = "";
        if(rowEnd - rowStart < seqCutOff){
            basins = getBasins(rowStart, rowEnd);
        }else{
            int mid = (rowStart+rowEnd)/2;
            forkAlgo top = new forkAlgo(terrain, rowStart, 1+mid, rows, columns, seqCutOff);
            forkAlgo bottom = new forkAlgo(terrain, mid, rowEnd, rows, columns, seqCutOff);

            top.fork();
            String bottomAns = bottom.compute();
            String topAns = top.join();
            basins = topAns+bottomAns;
        }
        return basins;
    }

    public String getBasins(int rs, int re){
        String toReturn = "";
        for(int i=rs; i<re-1; i++){
            for(int j=1; j<columns-1; j++){
                if(isBasin(i,j,(float) 0.01)){
                    toReturn = (toReturn + i + " " + j + "\n");
                }
            }
        }
        return toReturn;
    }

    public boolean isBasin(int row, int column, float difference){
        if((terrain[row][column-1]-terrain[row][column]) < difference)
            return false;
        else if((terrain[row][column+1]-terrain[row][column]) < difference)
            return false;
        else if((terrain[row-1][column]-terrain[row][column]) < difference)
            return false;
        else if((terrain[row+1][column] - terrain[row][column]) < difference)
            return false;
        else if((terrain[row+1][column-1] - terrain[row][column]) < difference)
            return false;
        else if((terrain[row+1][column+1] - terrain[row][column]) < difference)
            return false;
        else if((terrain[row-1][column-1] - terrain[row][column]) < difference)
            return false;
        else if((terrain[row-1][column+1] - terrain[row][column]) < difference)
            return false;
        else {
            //System.out.println(row +" "+ column +" "+ terrain[row][column]+ " "+ terrain[row][column-1]+ " "+terrain[row][column+1]+" "+terrain[row-1][column]+" "+terrain[row+1][column]);
            return true;
        }
    }
}
