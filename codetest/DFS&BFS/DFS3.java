import java.util.*;

public class DFS3 {
    
    public static int n,m;
    public static int[][] graph = new int[1000][1000];
    //조건이 1000개까지임

    //DFS로 특정 노드를 방문하기 연결된 모든 노드를 방문
    public static boolean dfs(int x, int y){ //좌표를 받음

        //주어진 범위 벗어난 경우 바로 종료
        if(x <= -1 || x >= n || y <= -1 || y >= m){
            return false;
        }
        //현재 노드를 방문하지 않았다면
        if(graph[x][y] == 0){

            //상,하 좌, 우의 위치도 모두 재귀적으로 호출
            dfs(x -1, y); 
            dfs(x, y -1); 
            dfs(x + 1, y); 
            dfs(x, y + 1); 
            return true;
        }
        return false; //이미 방문한곳

    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine(); //버퍼 비우기

        //2차원 배열 리스트 입력
        for( int i = 0; i <n; i++){ //행

            String str = sc.nextLine();

            for(int j = 0; j<m; j++){ //하나씩 대입
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        //모든 노드(위치)에 대해서 음료수 채우기
        int result = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){

                //현재 위치에서 DFS 수행
                if(dfs(i, j)){
                    result += 1;
                }

            }

        }

        System.out.println(result);

        
    }


}   
