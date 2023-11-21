import java.util.*;

public class Dy1 {

    //한번 계산된 결과를 메모이제이션하기 위한 배열 초기화
    public static long[] d = new long[100];

    //피보나치 함수를 재귀함수로 구현(탑다운 다이나믹 프로그래밍)
    public static long fibo(int x){
        //종료조건은 (1혹은 2일때 1을 반환)
        if(x == 1 || x ==2){
            return 1;
        }
        //이미 계산한적 있는 문제라면 그대로 반환
        if(d[x] != 0){
            return d[x];
        }
        //아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
        d[x] = fibo(x-1)+fibo(x-2);
        return d[x];
    }

    public static void main(String[] args) {
        System.out.println(fibo(50));
    }


}