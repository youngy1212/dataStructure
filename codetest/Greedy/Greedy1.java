import java.util.*;

public class main {

    public static void main(String[] args) {
        //배열의 크기 N, 숫자가 더해지는 횟수 M, 그리고 K(연속해서 더해질 수 있는)
        //둘째 줄에 배열의 값이 들어옴

        Scanner sc = new Scanner(System.in);
        
        //N,M,K를 공백 기준으로 구분하여 입력
        int n  = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        //N의 개수를 공백을 기준으로 구분하여 입럭
        int[] arr = new int[n];
        for(int i =0; i<arr.length; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int first = arr[n-1]; //가장큰수
        int second  = arr[n-2]; //두번 째로 큰수
        
        //가장 큰 수가  더해지는 횟수 계산
        int cnt = (m / (k+1)) * k;
        cnt += m % (k+1); 


        //반복 하기
        int resurt = 0;
        resurt += cnt * first;
        resurt += (m-cnt) * second;
        System.out.println(resurt);
        
    }
}
