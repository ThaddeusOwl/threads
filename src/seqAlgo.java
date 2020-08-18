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
            }
        }
    }

    public boolean isBasin(int row, int column, float difference){
        //boolean toReturn = false;
        if((terrain[row][column]-terrain[row][column-1]) <= difference)
            return false;
        if((terrain[row][column]-terrain[row][column+1]) <= difference)
            return false;
        if((terrain[row][column]-terrain[row-1][column]) <= difference)
            return false;
        return !((terrain[row][column] - terrain[row + 1][column]) <= difference);
    }

    public String getBasins(){
        for(int i=1; i<rows; i++){
            for(int j=1; j<columns; j++){
                if(isBasin(i,j,(float) 0.01)){
                    numBasins++;
                    basins = (basins + i + " " + j + "\n");
                }
            }
        }
        basins = (numBasins + "\n" + basins);
        return basins;
    }
}
