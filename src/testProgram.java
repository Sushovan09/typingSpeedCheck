public class testProgram {
    static int[][] arr;
    public static void main(String[] args) {

        arr= new int[10][5];

        for (int i=0; i<10; i++ ){
            for(int j=0; j<5; j++){
                arr[i][j]= i+j;
            }
        }

        for (int[] ar : arr) {
            for (int a : ar) {
                System.out.print(a+" ");
            }
            System.out.println();
        }
        System.out.println(arr.length);
        System.out.println(arr[1].length);
    }

}
