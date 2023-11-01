
class Tree{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    Node root; //root노드 생성
    public void makeTree(int[] a){ //배열정보를 받아서 트리를 만들기
        root = makeTreeR(a,0,a.length-1); //재귀호출 반복시작전에, 처음 데이터를 던져줌
        //재귀호출 끝나면 가장 꼭대기에 있는 root변수를 받아서 member 변수에 저장
    }

    public Node makeTreeR(int[] a, int start, int end ){ //배열 //시작//마지막
        if(start >end) return null; //시작점이 끝나는 점보다 커지면 재귀를 마침 
        //** 재귀는 끝나는 시점을 명확히 해야함. */
        int mid = (start +end) /2; //중간 지점 계산
        Node node = new Node(a[mid]); //노드생성
        node.left = makeTreeR(a, start, mid-1); //재귀호출 시작~중간앞까지 
        node.right = makeTreeR(a, mid+1, end);
        return node;
    }

    //트리가 잘 만들어 졌는지 확인
    public  void searchBTree(Node n, int find){  //시작 노드, 찾을 숫자

        if(find < n.data){ //현재 데이터보다 작은지?
            System.out.println("Data is smaller than "+n.data);
            searchBTree(n.left, find); //작은니까 작은값은 왼쪽값을 넘기고 반복호출
        }else if(find > n.data){
            System.out.println("Data is bigger than "+n.data);
            searchBTree(n.right, find);
        }else{
            System.out.println("Data found!");
        }

    }

}

public class ArrayTree {
    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i =0; i<a.length; i++){
            a[i] = i;
        }

        Tree t = new Tree();
        t.makeTree(a); //트리를 만들고 root에 저장 
        t.searchBTree(t.root, 2);

        //Data is smaller tah 4
        //data is bigger 1
        //date found!
    }
}
