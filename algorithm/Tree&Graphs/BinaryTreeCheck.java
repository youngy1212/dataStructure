public class BinaryTreeCheck {
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
        int size;

        Tree(int size){
            this.size = size;
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

        //inorder 방법을 변형하여 확인
        boolean isValidationBST1(){
            int[] array = new int[size];//노드갯수만큼 배열방
            inorder(root, array);
            for(int i = 1; i<array.length; i++){ //주의 1부터임!!!! inoder를 진행하며 이미 0은 저장되어있기 때문
                if(array[i] < array[i-1]){
                    return false; //정렬이 어긋나있음
                }
            }
            return true;
        }

        int index = 0; //몇번쨰 방까지 담았는지 기억할 정수

        void inorder(Node root, int[] array) {
            if(root != null){
                inorder(root.left, array);//왼쪽호출
                array[index] = root.data; //나
                index++;
                inorder(root.right, array); //오른족 호출
            }
        }

        Integer last_printed =null;
        boolean isValidationBST2(){
            return isValidationBST2(root);
        }
        boolean isValidationBST2(Node n){
            if(n == null) return true;
            if(!isValidationBST2(n.left)) return false;
            if(last_printed != null && n.data < last_printed){ //이전값을 현재 노드와 비교
                return false; //크다면 순서가 틀리니 false 반환
            }
            last_printed = n.data; //현재값을 이전에 할당
            if(!isValidationBST2(n.right)) return false;
            return true; //끝까지 오면
        }

        boolean isValidationBST3(){
            return isValidationBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        boolean isValidationBST3(Node n, int min, int max) {
            if(n == null){
                return true;
            }
            if(n.data <min || n.data>max ){ //해당을 벗어남!
                return false;
            }
            if(!isValidationBST3(n.left, min, n.data) || 
            !isValidationBST3(n.right, n.data, max)){
                return false;
            }

            return true;
        }
       
    }

    public static void main(String[] args) {
        BinaryTreeCheck binaryTreeCheck = new BinaryTreeCheck();
        Tree t = binaryTreeCheck.new Tree(10);
        System.out.println("Solution1 + using inorder : "+t.isValidationBST1());
        System.out.println("Solution2 + without array : "+t.isValidationBST2());
        System.out.println("Solution2 + Min Max : "+t.isValidationBST3());
    }
}
