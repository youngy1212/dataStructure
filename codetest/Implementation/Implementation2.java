import java.util.*;

public class Implementation2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        //H 입력받기
        int h  = sc.nextInt();
        int cnt = 0;

        for(int i =0; i <= h; i++){

            for(int j = 0; j < 60; j++){ //분
                
                for(int k = 0; k < 60; k++){ //시
                    if(check(i,j,k)) cnt++;
                }
            }

        }
    }

    //특정한 시각 안에 '3' 이 포함되는지 
    private static boolean check(int h, int m, int s) {

        if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3)
            return true;

        return false;
    }
}
