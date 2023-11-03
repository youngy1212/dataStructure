import java.util.*;

public class TreeBalanceArray {

    class Tree{
        class Node{
            int data;
            Node left;
            Node right;
            Node(int data){
                this.data = data;
            }
        }
        Node root;
        Tree(int size){
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
    }

    public static void main(String[] args) {
        TreeBalanceArray treeBalanceArray = new TreeBalanceArray();
        TreeBalanceArray.Tree t = treeBalanceArray.new Tree(5);
        ArrayList<LinkedList<Integer>> result = allSequences(t.root);
        for(LinkedList<Integer> l : result){
            for(int data : l){
                System.out.print(data);
            }
            System.out.println();
        }
        
    }

    //트리를 만드는 모든 배열을 가져오는 함수
    static ArrayList<LinkedList<Integer>> allSequences(Tree.Node node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if(node == null){
            result.add(new LinkedList<Integer>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);  //고정되는 맨앞 노드 (root가 됨)

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left); //왼쪽 노드들 (prefix + node.left) 가 담김 경우의 수 1
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right); //오른쪽 노드들 (prefix + node.right) 가 담김 경우의 수 2

        //왼쪽 오른쪽 경우의 수
        for(LinkedList<Integer> left :leftSeq){ 
            for(LinkedList<Integer> right : rightSeq){
                ArrayList<LinkedList<Integer>>  weaved = new ArrayList<LinkedList<Integer>>();
                weavedList(left,right,weaved,prefix);//경우의 수 만들기 왼쪽, 오른족, 저장할 곳, 고정노드 
                result.addAll(weaved);//경우의 수들를 부모에 반환
            }
        }
        return result;
    }

    //경우의 수를 만들어주는 함수
    private static void weavedList(LinkedList<Integer> first, LinkedList<Integer> second,
            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
             
        //재귀호출 정지하는 부분        
        if(first.size() == 0 || second.size() == 0){
            //파이프중 하나라도 비었다면 선택의 여지가 없음 반환
            LinkedList<Integer> result = new LinkedList<Integer>();
            for(int data : prefix) {
                result.add(data); //이전 파이프를 결과에 넣고 
            }
            result.addAll(first); //result에 가져다 붙임
            result.addAll(second);
            results.add(result); //결과에 추가
            return;
            
           
        }

        //첫번쟤째 데이터를 가져와 prefix에 붙임 (경우의수 1)
        int headFirst = first.removeFirst(); //파이프에서 한개 꺼내옴 
        prefix.addLast(headFirst); //prefix에 붙이고 
        weavedList(first, second, results, prefix); //선택의 여지가 없을 때까지 들어감
        prefix.removeLast(); 
        first.addFirst(headFirst);//두번째를 진행하기 위해 다시 원복해줌

        //두번쨰 (경우의 수2)
        int headSecound = second.removeFirst(); 
        prefix.addLast(headSecound); 
        weavedList(first, second, results, prefix); //또다른 경우의 수가 발생가능한가?
        prefix.removeLast();
        second.addFirst(headSecound); //변형한 데이터 원복

    }
}
