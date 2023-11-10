
import java.util.*;

public class Implementation {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); //버퍼 비우기
        String[] plans = sc.nextLine().split(" ");
        int x = 1, y = 1;


        // L,R, U, D 에따른 좌표 번화
        int[] dx = {0,0,-1,1};
        int[] dy = {-1, 1, 0,0};
        char[] moveType = {'L','R','U','D'};

        // 이동 계획 하나씩 확인
        for(int i = 0; i<plans.length; i++){
            char plan  = plans[i].charAt(0);

            //이동후 좌표
            int nx = -1, ny = -1;
            for(int j =0; j < 4; j++){
                if(plan == moveType[j]){
                    nx = x + dx[j];
                    ny = y + dy[j];
                }
            }
            //공간을 벗어나는 경우 무시
            if(nx < 1 || ny < 1 || nx > n || ny > n) continue;
            // 이동 수행 
            x = nx;
            y = ny;
            

        }

        System.out.println(x + " " + y);


    }
}