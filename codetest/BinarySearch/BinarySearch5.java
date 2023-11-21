import java.util.*;

public class BinarySearch5 {
    
    public static void main(String[] args) {
        
        //계수정렬로 부품 찾기
        Scanner sc = new Scanner(System.in);

        //N(가게의 부품 개수)
        int n = sc.nextInt();
        int[] arr = new int[1000001];
        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            arr[x] = 1;
        }

        //M(손님이 확인 요청한 부품 개수)
        int m = sc.nextInt();
        int[] targets = new int[n];
        for(int i = 0; i < m; i++){
            targets[i] = sc.nextInt();
        }
 
        //손님이 확인 요청한 부품 번호를 하나씩 확이
        for(int i = 0; i<m; i++){
            //해당 부품이 존재하는지 확인
            if(arr[targets[i]]==1){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }

        // Set 집합 자료형으로 해결 
        //집합(Set) 정보를 처리를 위한 HashSet 라이브러리 처리 
        HashSet<Integer> s = new HashSet<>();
        for(int i = 0; i<n; i++){
            int x = sc.nextInt();
            s.add(x);
        }

        //손님이 요청한 부품 개수는 동일하게 받아옴

        //손님이 요청한 부품번호를 하나씩 확인
        for(int i = 0; i <m; i++){
            //해당 부품이 존재하는지 확인
            if(s.contains(targets[i])){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }

    }
}
