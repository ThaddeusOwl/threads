import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class seqAlgo {

    int rows;
    int columns;
    float[][] terrain;
    int numBasins = 0;
    String basins = "";

    public seqAlgo(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName+".txt"));
        rows = file.nextInt();
        columns = file.nextInt();
        terrain = new float[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                terrain[i][j] = file.nextFloat();
                //System.out.println(terrain[i][j]);
            }
        }
        file.close();
    }

    public void getGrid(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                System.out.print(terrain[i][j]+",");
            }
            System.out.print("\n");
        }
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

    public String getBasins(){
        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(isBasin(i,j,(float) 0.01)){
                    numBasins++;
                    basins = (basins + i + " " + j + "\n");
                    //basins = (basins + i + " " + j +" "+ terrain[i][j]+ " "+ "\n");
                }
            }
        }
        basins = (numBasins + "\n" + basins);
        return basins;

    }
}
