import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;



public class SpeedCheckMain {

    public static String generateString(int n) {

                StringBuffer s = new StringBuffer();
                StringBuffer str = new StringBuffer();
                try {

                    FileInputStream input = new FileInputStream("word.txt");

                    // Reads the first byte
                    int i = input.read();
                    while(i != -1) {
                        s.append((char)i);
                        // Reads next byte from the file
                        i = input.read();
                    }
                    input.close();
                }

                catch(Exception e) {
                    e.getStackTrace();
                }

                String wordC = s.toString();
                String[] wordArr = wordC.split("\\r?\\n|\\r");

                // creating an object of Random class
                Random random = new Random();

                for(int i=0; i<n; i++){
                    int x = random.nextInt(3000);
                    str.append(wordArr[x]);
                    str.append(" ");
                }
                str.append(".");
                return str.toString();
    }

    public static int compare(String a, String b){


        String[] a1 = a.split(" ");
        String[] b1 = b.split(" ");
        int diff = a1.length - b1.length;
        System.out.println(diff);
        if(diff<0){
            diff = -(diff);
        }
        System.out.println(diff);
        int accu = a1.length - diff;
        System.out.println(accu);
        int min = a1.length< b1.length ? a1.length: b1.length;
        for(int i =0; i<min; i++){
            if(!(a1[i].equals(b1[i]))){
                accu--;
            }
        }
        System.out.println(accu);
        return accu;
    }


    public static void main(String[] args) throws InterruptedException{
        System.out.println("WELCOME TO MY SPELL CHECKING GAME");
        Scanner sc = new Scanner(System.in);
        int n = 100;
        System.out.println("enter anything to to continue: ");
        sc.nextLine();
        System.out.println("WRITE ALL THE HUNDRED WORDS AS FAST AS POSSIBLE WITH ACCURATE SPELLING\n");
        String testString = generateString(n);
        System.out.println(testString);

        Instant start = Instant.now();
        /* … The code being measured starts … */

        String input = sc.nextLine();

        /* … The code being measured ends … */
        Instant end = Instant.now();

        int accu = compare(testString,input);
        System.out.println("accuracy : "+accu+"%");
        Duration interval = Duration.between(start, end);
        System.out.println("total time taken in minutes: " + interval.getSeconds()/60.0 +"minutes");
        System.out.println("word per minutes(wpm) = "+(((double)(accu)/interval.getSeconds()))*60.0);

    }

}
