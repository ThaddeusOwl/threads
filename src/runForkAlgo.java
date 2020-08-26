import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class runForkAlgo {

    static long startTime = 0;
    //int seqCutOff;

    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }

    static final ForkJoinPool fjPool = new ForkJoinPool();
    static String results(float[][] grid, int sco){
        return fjPool.invoke(new forkAlgo(grid,1,grid.length, grid.length, grid[0].length, sco));
    }
    //public int numBasins = 0;

    public static void main(String[] args) throws FileNotFoundException {

        int seqCutOff;
        String fileName;

        if(args.length == 2){
            fileName = args[0];
            seqCutOff = Integer.parseInt(args[1]);
        }else{
            fileName = "large_in";
            seqCutOff = 50;
        }

        int rows;
        int columns;
        float[][] terrain;
        //fileName = "large_in";

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

        tick();
        String toPrint = results(terrain, seqCutOff);
        float time = tock();

        System.out.println("Run took " + time + " seconds with " + nrOfProcessors + "processors");
        int numBasins = toPrint.split("\n").length;
        //System.out.println(numBasins+ "\n" + toPrint);
        file.close();


    }
}
