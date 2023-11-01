public class TreeBalance {

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
            //노밸런스하게 만들기
            root.right.right.right.right = new Node(10);//false
            root.right.right.left = new Node(11);
        }

        Node makeBST(int start, int end){
            if(start > end) return null;
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start,mid-1);
            node.right = makeBST(mid+1, end);
            return node;
        }

        boolean isBalanced(Node root){
           if(root == null){
            return true;
           } //마지막 노드를 지나면 ture 반환
            int heightDiff = getHeight(root.left) - getHeight(root.right);
            if(Math.abs(heightDiff)>1){ //1이상 차이가 나면
                return false;
            } else{
                return isBalanced(root.left) && isBalanced(root.right); //왼오 둘다 밸런시가 맞는지 체크
            }
        }
        //가장 깊은 노드 길이 찾기
        int getHeight(Node root){
            if(root == null) return -1;
            return Math.max(getHeight(root.left), getHeight(root.right))+1; //더 건 노드로 +1 하여 반환

        }
    
        //방법2 길이를 재는 함수
        int checkHeigh(Node root){
            if(root ==null) return -1;//재귀호출이 끝나는 경우 (-1):레벨에서 하나 뺴라! null은 한칸 더갔다는 것이니
            int leftHeight = checkHeigh(root.left); //왼쪽길이
            if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //false반환!
            int rightHeight = checkHeigh(root.right); 
            if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            int heightDiff = leftHeight-rightHeight; //서브트리 결과 비교
            if(Math.abs(heightDiff)>1){
                return Integer.MIN_VALUE; //false 반환
            }else{
                return Math.max(leftHeight, rightHeight)+1; //큰값에 1을 더해 반환
            } //1씩 증가하여 길이로 사용
        }

        //재귀함수를 호출해줄 함수
        boolean isBalanced2(Node root){
            return checkHeigh(root) != Integer.MIN_VALUE; //true 반환
        }

        //레벨 정보를 저장할 class
        class Level{
            int min = Integer.MIN_VALUE; 
            int max = Integer.MAX_VALUE; 
        }

        boolean isBalanced3(Node root){ //시작노드를 받음
            Level obj = new Level();
            checkBalanced(root,obj,0);//시작노드, 저장할 obj,레벨 0부터
            if(Math.abs(obj.min = obj.max) >1 ) return false; //1이상이면 unbalance
            else return true;
        }

        //재귀호출하며 obj업데이트 하는 함수
        void checkBalanced(Node node, Level obj, int level){
            if(node == null){ //길이는 맨지막에 하면됨
                if(level<obj.min) obj.min = level; //오브젝트를 비교 
                else if(level>obj.max) obj.max = level;
                return;
            }
            checkBalanced(node.left, obj, level+1);
            checkBalanced(node.right, obj, level+1);
        }
        
    }

    public static void main(String[] args) {
        TreeBalance TreeBalance = new TreeBalance();
        TreeBalance.Tree t = TreeBalance.new Tree(10);
        System.out.println(t.isBalanced(t.root));
        System.out.println(t.isBalanced2(t.root));
        System.out.println(t.isBalanced3(t.root));

    }
    
}
