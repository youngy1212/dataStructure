
import java.util.*;

public class Sorting3 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        //n을 입력받기
        int n = sc.nextInt();

        //N개의 정소를 입력받아 리스트에 저장
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        //기본정렬 라이브러리를 이용해서 내림차순 정렬
        Arrays.sort(arr,Collections.reverseOrder());

        for(int i = 0; i <n; i++){
            System.out.println(arr[i]+" ");
        }

    }
}
