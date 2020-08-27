import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class runSeqAlgo {

    static long startTime = 0;

    private static void tick(){ startTime = System.currentTimeMillis(); }
    private static float tock(){return (System.currentTimeMillis() - startTime) / 1000.0f;}

    public static void main(String[] args) throws FileNotFoundException {

        String fileName;

        if(args.length == 1){
            fileName = args[0];
        }else{
            fileName = "1000x1000";

        }

        Scanner file = new Scanner(new File(fileName+".txt"));
        int rows = file.nextInt();
        int columns = file.nextInt();
        float[][] terrain = new float[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                terrain[i][j] = file.nextFloat();
                //System.out.println(terrain[i][j]);
            }
        }


        seqAlgo x = new seqAlgo(terrain);
        tick();
        String y = x.getBasins();
        float time = tock();
        System.out.println("Run took " + time + " seconds");

        file.close();
        System.out.println(y);
        //x.getGrid();


    }

}
