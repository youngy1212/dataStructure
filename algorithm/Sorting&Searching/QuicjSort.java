
public class QuicjSort {

    private static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        int part2 = partition(arr, start, end); //파티션을 나누고 첫번째 값을 받아옴
        if(start < part2 -1){ //오른쪽 파티션이 시작점 바로 다음에서 시작시 왼쪽은 하나뿐이니 정렬X 
            quickSort(arr, start, part2 -1); //재귀적으로 호출 (왼쪽)
        }
        if(part2 < end){ //오른쪽 파티션 재귀호출
           quickSort(arr, part2, end);
        }
    }
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start+end)/2];
        while (start <= end) {  //포인터를 한칸씩 앞으로 옮김 
            while (arr[start] < pivot) start++; //피벗보다 작으면 무시
            while (arr[end] > pivot) end--; //멈추고 엔드포인트 이동
            if(start <= end){ //돌다 시작점과 끝점이 만나지 못했는지
                swap(arr, start , end);
                start++;
                end--;
            }
        }
        return start; //오른쪽 첫번째 배열방 인덱스가 들어가게됨.
       
    }

    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] =arr[end];
        arr[end] = tmp;
    }

    private static void printArray(int[] arr ){
        for(int data: arr){
            System.out.print(data + " ,");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr= {3,9,4,7,5,0,1,6};
        quickSort(arr);
        printArray(arr);
        
    }
    
}