import java.util.*;

public class Greedy3 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //입력받기
        int n = sc.nextInt();//N값
        int k = sc.nextInt(); //나눌수 있는 값
        int result = 0;

        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            int target = (n/k) * k;
            result += (n - target); //나머지 값 result에 더해줌.
            n = target; //배수 만들어줌.


            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if(n < k){  
                break;
            }
            result += 1;
            n /= k;
        }


        System.out.println(result);

    }
}
