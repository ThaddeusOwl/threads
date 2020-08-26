import java.util.concurrent.RecursiveTask;

public class forkAlgo extends RecursiveTask<String> {

    int rowStart;
    int rowEnd;
    int rows;
    int columns;
    float[][] terrain;
    int seqCutOff;
    //static final int seqCutOff;
    //int numBasins = 0;


    public forkAlgo(float[][] g, int rs, int re, int r, int c, int sco){
        terrain = g;
        rowStart = rs;
        rowEnd = re;
        rows = r;
        columns = c;
        seqCutOff = sco;
        //numBasins = count;
    }

    protected String compute() {
        //int numBasins = 0;
        String basins = "";
        if(rowEnd - rowStart < seqCutOff){
        //if(rowEnd - rowStart < (rows+10)/2){
            basins = getBasins(rowStart, rowEnd);
            //String basins = "";
            //basins = (basins+getBasins(rowStart, rowEnd));
            //return basins;
        }else{
            int mid = (rowStart+rowEnd)/2;
            forkAlgo top = new forkAlgo(terrain, rowStart, 1+mid, rows, columns, seqCutOff);
            forkAlgo bottom = new forkAlgo(terrain, mid, rowEnd, rows, columns, seqCutOff);

            /*top.fork();
            bottom.fork();
            String topAns=top.join();
            String bottomAns=bottom.join();*/

            top.fork();
            String bottomAns = bottom.compute();
            String topAns = top.join();
            basins = topAns+bottomAns;
            //basins = (basins+(topAns+bottomAns));
            //return basins;
            //return bottomAns;
        }
        return basins;
    }

    public String getBasins(int rs, int re){
        String toReturn = "";
        for(int i=rs; i<re-1; i++){
            for(int j=1; j<columns-1; j++){
                if(isBasin(i,j,(float) 0.01)){
                    //numBasins++;
                    toReturn = (toReturn + i + " " + j + "\n");
                    //basins = (basins + i + " " + j +" "+ terrain[i][j]+ " "+ "\n");
                }
            }
        }
        return toReturn;
    }

    public boolean isBasin(int row, int column, float difference){
        //boolean toReturn = false;
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
