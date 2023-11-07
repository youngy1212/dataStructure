
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr= {3,9,4,7,5,0,1,6};
        selectionSort(arr);
        printArray(arr);
    }
    
    private static void selectionSort(int[] arr){
        selectionSort(arr, 0); //배열, 정렬이안된 시작index
    }

    private static void selectionSort(int[] arr, int start) {
        if(start < arr.length -1){
            int min_index = start; //변수 선언
            for(int i = start; i<arr.length; i++){
                if(arr[i] < arr[min_index]) min_index = i;
                //더 작다면 업데이트
            }
            swap(arr,start,min_index);
            selectionSort(arr,start+1);
        }
    }

    private static void swap(int[] arr, int source, int target) {
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }

    private static void printArray(int[] arr ){
        for(int data: arr){
            System.out.print(data + " ,");
        }
        System.out.println();
    }
    
}