import java.util.ArrayList;
import java.util.LinkedList;

public class TreeLevel {
    
   class Tree{
        class Node{ //이진트리 노드
            int data;
            Node left;
            Node right;
            Node(int data){
                this.data = data;
            }
        }
        Node root;//root 저장
       
        Tree(int size){//생성과 동시에 이진트리 만들기 
            root = makeBST(0,size-1);
        }
        Node makeBST(int start, int end){
            if(start > end) return null;
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start,mid-1);
            node.right = makeBST(mid+1, end);
            return node;
        }
        
        //재귀호출을 할때 Level을 함수의 인자로 받는 방법1
        ArrayList<LinkedList<Node>> BSTtoList(){
            //재귀함수를 호출하기전에 함수를 초기값들을 던져줄 함수
            ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
            BSTtoList(root,lists,0); //재귀호출(2진트리 시작노드,레벨 )
            return lists;
        }

        //재귀함수 
        void BSTtoList(Node root, ArrayList<LinkedList<Node>> lists, int Level) {
            if(root ==null) return; //null경우 나감 : 멈출 부분!
            LinkedList<Node> list = null; //node를 담을 List
            if(lists.size() == Level){ //새로운 레벨의 노드를 호출 
                list = new LinkedList<Node>(); //새로운 노드 생성
                lists.add(list); //배열방 추가
            }else{ //이미 해당레벨의 리스트가 존재
                list = lists.get(Level);//노드 추가를 위해 기존 시작노드 획득
            }
            list.add(root); //노드를 추가
            //자식노드로 재호출
            BSTtoList(root.left, lists, Level+1); 
            BSTtoList(root.right, lists, Level+1);
        }

        //방법 2: BFS 변형방법
        ArrayList<LinkedList<Node>> BSTtoList2(){
            //결과값을 받을 배열
            ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();

            LinkedList<Node> current = new LinkedList<Node>();//현재레벨을 담을 LinkedList
            if(root != null){
                current.add(root); //초기값 1번쨰 레벨에는 해당 노드만
            }
            while(current.size() > 0) { //현재레벨이 노드가 존재하지 않을때까지
                result.add(current); //현재레벨이 부모가 됨.
                LinkedList<Node> parents = current; //해당은 부모레벨
                current = new LinkedList<Node>();//현재레벨은 새로시작
                for(Node parent : parents){ //부모의 모든 노드를 돌며
                    if(parent.left != null) current.add(parent.left); //왼쪽 오른족 자식이 있으면 추가
                    if(parent.right != null) current.add(parent.right);
                }
                //반복! 
            }
            return result; //결과를 반환
        }
        void printList(ArrayList<LinkedList<Node>> arr){
            for(LinkedList<Node> list : arr){
                for(Node node : list){
                    System.out.print(node.data+" ");
                }
                System.out.println();
            }
        }

    }
        
    
    public static void main(String[] args) {
        TreeLevel TreeLevel = new TreeLevel();
        TreeLevel.Tree t = TreeLevel.new Tree(10);
        t.printList(t.BSTtoList());
        t.printList(t.BSTtoList2());

    }
}
