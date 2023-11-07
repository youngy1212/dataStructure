public class MergeSort {
    public static void main(String[] args) {
        int[] arr= {3,9,4,7,5,0,1,6};
        mergeSort(arr);
        printArray(arr);
    }

    private static void mergeSort(int[] arr){
        int[] tmp = new int[arr.length]; //정렬할 공간
        mergeSort(arr, tmp,0,arr.length-1); //배열, 임시저장, 시작, 끝 
    }

    private static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if(start < end){ //재귀호출
            int mid = (start+end)/2;
            mergeSort(arr, tmp, start, mid); //앞부분 
            mergeSort(arr, tmp, mid+1, end); //뒷부분 
            merge(arr, tmp, start, mid, end); //나눠진 배열방 병합
        }
    }

    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        for(int i = start; i<= end; i++){ //임시저장소에 저장된 배열을 필요한 만큼 복사
            tmp[i] = arr[i];
        }
        int part1 = start; //첫번쨰
        int part2 = mid+1;  //두번쨰 방의 index 
        int index = start; //결과 배열방 어디에 저장할지
        while (part1 <= mid && part2 <= end) { //첫번째나 , 두번째 방의 끝까지 도착할 때 까지
            if(tmp[part1] <= tmp[part2]){ //앞방이 더 작다면
                arr[index] = tmp[part1]; 
                part1++;
            }else{
                arr[index] = tmp[part2];
                part2++;
            }
            index++; //어느배열을 옮기더라도 인덱스 증가
        }
        //데이터가 남을 경우!
        for(int i = 0; i<=mid-part1; i++){  //앞쪽이 남을 경우!
            arr[index + i] = tmp[part1+i];
        }
    }

    private static void printArray(int[] arr ){
        for(int data: arr){
            System.out.print(data + " ,");
        }
        System.out.println();
    }
}
