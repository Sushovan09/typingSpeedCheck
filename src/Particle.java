import java.io.FileInputStream;
import java.util.Scanner;

public class Particle {
    public static String[] properties = { "Charge" , "Electron Number", "Muon Number" , "Tauon Number", "Baryon Number"};
    public static int[][] info = null;


    public static String[] particleName;

    public static void generateInfo() {

        int totalP = 5;
        StringBuffer s = new StringBuffer();
        //StringBuffer str = new StringBuffer();
        try {

            FileInputStream input = new FileInputStream("particleProperties.txt");

            // Reads the first byte
            int i = input.read();
            while (i != -1) {
                s.append((char) i);
                // Reads next byte from the file
                i = input.read();
            }
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        String wordC = s.toString();
        String[] wordArr = wordC.split("\\r?\\n|\\r");

        int len = wordArr.length;
        particleName = new String[len];

        info = new int[len][];

        for (int i = 0; i < len; i++) {
            String[] s2 = wordArr[i].split(" ");
            particleName[i] = s2[1];

            String[] s3 = s2[0].split(",");
            totalP = s3.length;
            info[i] = new int[totalP];            ///// this fucking line

            for (int j = 0; j < totalP; j++) {
                info[i][j] = Integer.parseInt(s3[j]);
            }
        }
    }

    public static boolean validate(int[] lhs, int[] rhs){



        int[] lhsAdd = new int [info[1].length];
        int[] rhsAdd = new int [info[1].length];

        for (int i=0; i<info[1].length; i++) {
            lhsAdd[i]=0;
            rhsAdd[i]=0;
        }

        for(int val : lhs){
            for(int i=0; i<info[1].length; i++){
                lhsAdd[i]+=info[val][i];
            }
        }

        for(int val : rhs){
            for(int i=0; i<info[1].length; i++){
                rhsAdd[i]+=info[val][i];
            }
        }


        for(int i=0; i<info[1].length; i++){
            if(!(lhsAdd[i]==rhsAdd[i])){
                System.out.println(properties[i]+" not conserved -> LHS addition "+lhsAdd[i]+" != RHS addition "+rhsAdd[i]);
                return false;
            }
        }
        return true;

    }


    public static void main(String[] args) {
        int counter=0;

        generateInfo();

        for ( String st :particleName) {
            System.out.println( st+" -> "+counter);
            counter++;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print(" enter code for corresponding particle LHS : ");
        String lhs = sc.nextLine();
        System.out.print(" enter code for corresponding particle RHS : ");
        String rhs = sc.nextLine();

        lhs = lhs.trim();
        rhs = rhs.trim();

        String[] lhsA  = lhs.split(" ");
        String[] rhsA  = rhs.split(" ");

        int[] lhsArr = new int[lhsA.length];
        int[] rhsArr = new int[rhsA.length];

        for(int i=0; i<lhsA.length; i++){
            lhsArr[i] = Integer.parseInt(lhsA[i]);
        }

        for(int i=0; i<rhsA.length; i++){
            rhsArr[i] = Integer.parseInt(rhsA[i]);
        }
        boolean b = validate(lhsArr,rhsArr);

        System.out.println(b==true?"possible":"not possible");

        return;
    }
}
