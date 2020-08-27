import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Testing {

    static long startTime = 0;
    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){
        return (System.currentTimeMillis() - startTime) ;
    }
    static final ForkJoinPool fjPool = new ForkJoinPool();
    static String getBasins(float[][] grid, int sco){
        return fjPool.invoke(new forkAlgo(grid,1,grid.length, grid.length, grid[0].length, sco));
    }

    public static float forkRunAvg(float[][] grid, int sco){
        float toReturn = 0;
        for(int i=0; i<25; i++){
            System.gc();
            tick();
            getBasins(grid, sco);
            float time = tock();
            if(i>4){
                //System.out.println(time);
                toReturn += time;
            }
        }
        return (toReturn/20);
    }

    public static float seqRunAvg(float[][] grid){
        //startTime = 0;
        seqAlgo x = new seqAlgo(grid);
        float toReturn = 0;
        for(int i=0; i<20; i++){
            System.gc();
            tick();
            x.getBasins();
            float time = tock();
            //System.out.println(time);
            toReturn += time;
            //startTime = 0;

        }
        return (toReturn/20);
    }

    public static void main(String[] args) throws FileNotFoundException {

        int rows;
        int columns;
        float[][] terrain;
        String fileName;
        int numThreads;

        fileName = args[0];
        numThreads = Integer.parseInt(args[1]);

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

        int seqCutOff = 2 + (rows/numThreads);

        float f = forkRunAvg(terrain, seqCutOff);
        float s = seqRunAvg(terrain);

        float speedUp = s/f;

        System.out.println("Avg for SeqAlgo with size " + fileName + " :  " + s );
        System.out.println("Avg for ForkAlgo with size " + fileName + " :  " + f );
        System.out.println("Speedup:  "+ speedUp);

    }

}
