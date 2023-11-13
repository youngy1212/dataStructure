
import java.util.*;

public class sorting {

    public static final int MAX_VALUE = 9;
    
    public static void main(String[] args) {

        int n = 10;
        int[] arr = {7,6,9,0,3,1,6,2,4,8};

        //선택정렬
        for(int i = 0; i < n; i++){
            int min_index = i;
            for(int j = i+1; j<n; j++){
                if(arr[min_index] > arr[j]){
                    min_index = j;
                }
            }
            //스와프 
            int tmep = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = tmep;
        
        }

        //삽입정렬
        for(int i =1; i<n; i++){
            //인덱스 i부터 1까지 감소하며 반복하는 문법
            for(int j = i; j>0; j--){
                //한칸씩 왼쪽으로 이동
                if(arr[j] < arr[j-1]){
                    //스와프
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                // 자기보다 작은 데이터를 만나면 그 위치에서 멈춤
                else break;
            }
        }

        //퀵 정렬
        quickSort(arr, 0, n - 1);


        //계수정렬
        int n2 = 15;
        // 모든 원소의 값이 0보다 크거나 같다고 가정
        int[] arr2 = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        // 모든 범위를 포함하는 배열 선언(모든 값은 0으로 초기화)
        int[] cnt = new int[MAX_VALUE + 1];

        for (int i = 0; i < n; i++) {
            cnt[arr[i]] += 1; // 각 데이터에 해당하는 인덱스의 값 증가
        }
        for (int i = 0; i <= MAX_VALUE; i++) { // 배열에 기록된 정렬 정보 확인
            for (int j = 0; j < cnt[i]; j++) {
                System.out.print(i + " "); // 띄어쓰기를 기준으로 등장한 횟수만큼 인덱스 출력
            }
        }


        
    }

    //퀵정렬
    private static void quickSort(int[] arr, int start, int end) {
        if(start >= end) return; //원소가 한개인 경우 리턴
        int pivot = start; // 피벗은 첫 번째 원소
        int left = start + 1;
        int right = end;
        while (left <= right){
            //피벗보다 큰 데이터를 찾을 때까지 반복
            while (left <= end && arr[left] <= arr[pivot]) left++;
             //피벗보다 작은 데이터를 찾을 때까지 반복
            while (right > start && arr[right] >= arr[pivot]) right--;
            //엇갈렸다면 작은 데이터와 피벗을 교체
            if(left > right){
                int temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            }
            // 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
            else{
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            
        }

        // 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);



    }

    
    
}