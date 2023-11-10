import java.util.Scanner;

public class main {

    //숫자 카드 게임 
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //공백을 기준으로 구분하여 입력
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        //한줄씩 입력 받아 확인하기
        for(int i =0; i<n; i++){
            int min_value = Integer.MAX_VALUE;
            for(int j = 0; j<m; j++){
                int x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }
            //가장 작은 수중 가장 큰 수 
            result = Math. max(result, min_value);
        }


        System.out.println(result); //최종 답

    }
}
