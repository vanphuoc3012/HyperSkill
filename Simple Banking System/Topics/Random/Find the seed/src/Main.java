import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int minOfMax = Integer.MAX_VALUE;
        int seed = 0;

        for(int i = A; i <= B; i++){
            Random rand = new Random(i);
            int maximum = 0;
            for (int j = 0; j < N; j++ ){
                int findMax = rand.nextInt(K);
                if(findMax > maximum){
                    maximum = findMax;
                }
            }
            if(maximum < minOfMax){
                minOfMax = maximum;
                seed = i;
            }
        }

        System.out.println(seed);
        System.out.println(minOfMax);

    }
}