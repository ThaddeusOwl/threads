import java.io.FileNotFoundException;

public class runSeqAlgo {

    static long startTime = 0;

    private static void tick(){ startTime = System.currentTimeMillis(); }
    private static float tock(){return (System.currentTimeMillis() - startTime) / 1000.0f;}

    public static void main(String[] args) throws FileNotFoundException {

        String fileName;

        if(args.length == 1){
            fileName = args[0];
        }else{
            fileName = "large_in";

        }

        seqAlgo x = new seqAlgo(fileName);
        tick();
        String y = x.getBasins();
        float time = tock();
        System.out.println("Run took " + time + " seconds");


        //System.out.println(y);
        //x.getGrid();


    }

}
