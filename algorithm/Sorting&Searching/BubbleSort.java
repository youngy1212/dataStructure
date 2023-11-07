public class BubbleSort {
    public static void main(String[] args) {
        int[] arr= {3,9,4,7,5,0,1,6};
        bubbleSort(arr);
        printArray(arr);
        
    }
    private static void bubbleSort(int[] arr){
        bubbleSort(arr, arr.length-1); //배열의 주소와 정렬이 안된 마지막 index
    }
    private static void bubbleSort(int[] arr, int last) {
        if(last > 0){
            for(int i =1; i<=last; i++){
                if(arr[i-1] > arr[i]){ //내 앞에 있는애가 나보다 큰가>
                    swap(arr, i-1, i);
                }
            }
            bubbleSort(arr, last -1); //맨마지막하나는 정렬되었으니
            //-1 하고 다시 재귀호출
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
