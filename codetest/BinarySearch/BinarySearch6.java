import java.util.Scanner;

public class BinarySearch6 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 떡의 개수(N)와 요청한 떡의 길이(M)
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 각 떡의 개별 높이 정보 
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        //이진탐색을 위한 시작점과 끝점 설정
        int start = 0;
        int end = (int)1e9; //최대값
        
        //이진 탐색 수행(반복적) : 파라메트릭에서는 반복문 이진검색이 효과적
        int result = 0;
        while (start <= end) {
            long total = 0;
            int mid = (start+end)/2;

            for(int i = 0; i<n; i++){
                //잘렸을때 떡 양 계산
                if(arr[i]>mid) total += arr[i] - mid;
            }

            if(total < m ){//떡양이 부족한 경우 더 많이 자르기(왼쪽 부분 탐색)
                end = mid-1;
            }else{ //떡양이 충분한 경우 덜 자르기 (오른쪽으로 부분 탐색)
                result = mid; // 최대한 덜 잘랐을 때가 정답이므로, 여기에서 result에 기록 
                start = mid + 1;
            }
            
        }
        System.out.println(result);
    }
}
