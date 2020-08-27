/**
@author: Ahmed
Takes in 2 or 0 arguments
2: 1st=fileName 2nd=number of threads
0: default values: 1000x1000 4
*/



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class runForkAlgo {

    static long startTime = 0;

    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){return (System.currentTimeMillis() - startTime) / 1000.0f;}

    static final ForkJoinPool fjPool = new ForkJoinPool();
    static String getBasins(float[][] grid, int sco){
        return fjPool.invoke(new forkAlgo(grid,1,grid.length, grid.length, grid[0].length, sco));
    }


    public static void main(String[] args) throws FileNotFoundException {

        int numThreads;

        String fileName;

        if(args.length == 2){
            fileName = args[0];
            numThreads = Integer.parseInt(args[1]);
        }else{
            fileName = "1000x1000";
            numThreads = 4;
        }

        int rows;
        int columns;
        float[][] terrain;

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

        Runtime runtime = Runtime.getRuntime();
        int nrOfProcessors = runtime.availableProcessors();
        System.gc();

        int seqCutOff = 2 + (rows/numThreads);

        tick();
        String toPrint = getBasins(terrain, seqCutOff);
        float time = tock();

        //System.out.println("Run took " + time + " seconds with " + nrOfProcessors + "processors");
        //System.out.println(time);
        int numBasins = toPrint.split("\n").length;
        System.out.println(numBasins+ "\n" + toPrint);
        file.close();




    }
}
