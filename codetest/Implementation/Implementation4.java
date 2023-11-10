import java.util.*;

public class Implementation4 {

    public static int n, m, x, y, direction;
    // 방문한 위치를 저장하기 위한 맵을 생성하여 0으로 초기화
    public static int[][] d = new int[50][50];
    // 전체 맵 정보
    public static int[][] arr = new int [50][50];

    // 0 북, 1 동, 2 남, 3 서 방향 정의
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, M 입력받기
        n = sc.nextInt();
        m = sc.nextInt();

        //현재 캐릭터의 x 좌표 y좌표, 방향 입력받기
        x = sc.nextInt();
        y = sc.nextInt();
        direction = sc.nextInt();
        d[x][y] = 1; //현재 좌표 방문처리

        // 전체 맵 정보 입력 받기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        //시뮬레이션 시작
        int cnt = 1; //이동한 횟수
        int tru_time = 0;
        while (true) {
            
            //왼쪽으로 회전
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            //회전한 이후 정면에 가보지 않은 칸이 존재하는 경우
            if(d[nx][ny] == 0 && arr[nx][ny] == 0){
                d[nx][ny] = 1;
                x = nx;
                y = ny;
                cnt += 1;
                tru_time  = 0;
                continue;
            }else{ //회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
                tru_time += 1;
            }

            if(tru_time == 4){
                nx = x - dx[direction];
                ny = y - dy[direction];
                // 뒤로 갈 수 있다면 이동하기
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }else{
                    break;
                }

                tru_time = 0;
            }

        }
        
        
        System.out.println(cnt);

    }


    private static void turn_left() {
        direction -= 1;
        if(direction == -1) direction = 3;
    }
    
}
