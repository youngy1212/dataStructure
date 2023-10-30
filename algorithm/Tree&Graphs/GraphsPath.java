import java.util.LinkedList;

/**
 * GraphsPath
 * 두 지점의 경로 찾기
 * DFS/BFS
 * 
 */


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

    Node[] nodes; //노드를 관리할 배열방

    Graph(int size){
        nodes = new Node[size];
        for(int i =0; i<size; i++){
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int i1, int i2){
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if(!n1.adjacent.contains(n2)){ 
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
        
    }

    void initMarks(){ //확실히 체크하기 위해 초기화 
        for(Node n : nodes){
            n.marked = false;
        }
    }

    //노드로 변환해서 호출
    boolean search(int i1,int i2){
        return search(nodes[i1],nodes[i2] );
    }

    boolean gsearch(Node start, Node end){
        //경로체크 BFS!
        initMarks();
        LinkedList<Node> q = new LinkedList<Node>(); //q로 사용
        q.add(start);
        while (!q.isEmpty()) { 
            Node root = q.removeFirst();
            if(root == end){ //같다면?
                return true;
            }
            for(Node n : root.adjacent){ //q에 들어오지 않은 노드를 추가
                if(n.marked == false){
                    n.marked = true;
                    q.add(n);
                }
            }
        }
        return false; //모든 작업을 맞쳐도 root가 end를 만나지 못한다면 false
    }
}


public class GraphsPath {

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        //g.addEdge(3, 5); 
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);
        System.out.println(g.search(1, 8));
        
    }
    
}