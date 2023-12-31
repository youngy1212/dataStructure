import java.util.*;


public class BFS {
    

    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();


    //bfs 함수
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        //현재 노드를 방무 처리
        visited[start] = true;

        //큐가 빌때까지 반복
        while (!q.isEmpty()) {
            //큐에서 하나의 원소 뽑아 출력
            int x = q.poll();
            System.out.println(x+" ");
            //해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
            for(int i = 0; i < graph.get(x).size(); i++){
                int y = graph.get(x).get(i);
                if(!visited[y]){
                    q.offer(y);
                    visited[y] = true;
                }
            }
        }
        
    }
}
