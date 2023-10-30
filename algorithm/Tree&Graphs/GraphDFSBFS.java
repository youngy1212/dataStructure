import java.util.LinkedList;
import java.util.Stack;
import java.util.NoSuchElementException;

class Queue<T> {
    
    //같은 타입을 받는 노드 선언
    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }

    //큐는 앞뒤로 주소를 알아야함.
    private Node<T> first;
    private Node<T> last;


    //추가하기
    public void add(T item){
        Node<T> t = new Node<T>(item);

        if(last != null){ //마지막 노드가 있다면
            last.next = t; //새로생성한 노드 붙여주기
        }
        last = t;
        if(first == null){
            first = last; //첫 번째 노드라면
        }
    }

    //지우기
    public T remove(){
        if(first == null){
            throw new NoSuchElementException();
        }

        T data = first.data;
        first = first.next; 

        if(first == null){
            last = null;
        }
        return data;
    }

    public T peek(){
        if(first == null){
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty(){
        return first == null;
    }


}


class Graph{
    class Node{
        int data;
        LinkedList<Node> adjacent; //근접 노드
        boolean marked; //방문했는지
        Node(int data){
            this.data= data;
            this.marked = false;
            adjacent = new LinkedList<Node>();
        }
    }

    //그래프로 만들기
    //노드들을 저장할 배열
    Node[] nodes;
    Graph(int size){
        nodes = new Node[size]; //편의상 방크기 동일
        for(int i =0; i<size; i++){
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int i1, int i2){ //두노드의 관계를 저장하는 함수
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if(!n1.adjacent.contains(n2)){ //상대방이 있는지 확인하고 
            n1.adjacent.add(n2); //없다면 서로추가
        }
        if(n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
    }

    void dfs(){
        dfs(0); //그냥 호출시 0부터 시작하는 걸로
    }
    void dfs(int index){
        //순회결과 출력
        Node root = nodes[index];
        Stack<Node> stack = new Stack<Node>();
        stack.push(root); //스택에 추가
        root.marked = true; //스택에 추가됐음.
        while (!stack.isEmpty()) { 
            Node r = stack.pop(); //데이터 가져오기
            
            for(Node n : r.adjacent){ //자식들 추가
                if(n.marked  == false){
                    n.marked = true;
                    stack.push(n);
                }
            }
            visit(r);
        }
    }


    //BFS
    void bfs(){
        bfs(0);
    }

    void bfs(int index){
        Node root = nodes[index];
        Queue<Node> queue = new Queue<Node>();
        queue.add(root); //추가 
        root.marked = true;
        while (!queue.isEmpty()) {
            Node r = queue.remove();
            for(Node n: r.adjacent){
                if(n.marked == false){
                    n.marked = true;
                    queue.add(n);
                }
            }
            visit(r);
        }
    }


    //재귀호출을 이용한 dfs
    void dfsR(Node r){
        if(r == null) return; //null이라면 바로퇴장
        r.marked = true;
        visit(r);//먼저 출력
        for(Node n : r.adjacent){ //자식들 추가
            if(n.marked  == false){
              dfsR(n);
            }
        }
    }

    void dfsR(int index){
        Node r = nodes[index];
        dfsR(r);
    }

     void dfsR(){
        dfsR(0);
    }
    
    //출력함수
    void visit(Node n){
        System.out.println(n.data+"  ");
    }

}




public class GraphDFSBFS {
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5); 
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);
        //g.dfs(); //0 1 3 5 7 6 8 4 2
        //g.bfs(); //0 1 2 3 4 5 6 7 8
        //g.dfsR(); //0 1 2 4 3 5 6 8 7 

        g.dfs(3); //3 5 7 6 8 4 (뒤에안나옴) 2 1 0

    }
}
