
public class TreeRecreate {

    
    class Tree{
        class Node{ 
            int data;
            Node left, right;
            Node(int data){
                this.data = data;
            }
        }
        Node root;
        int pIndex = 0; //배열을 어디까지 읽었는지 체크하는 index
       
        public void buildTreeByInPre(int in[], int pre[]){
            pIndex = 0;
            root = buildTreeByInPre(in, pre,0,in.length-1);
        }

        //트리를 만들 재귀함수 preorder와 inorder 
        private Node buildTreeByInPre(int[] in, int[] pre, int start, int end) {
            if(start > end)return null; //돌 다가 지나치면 호출을 종료
            Node node = new Node(pre[pIndex++]); //preorder 순서를 담은 배열방 데이터를 가쟈옴
            //++ 하며 다음방을 가르키게함.
            if(start == end) return node; //시작과 끝이 같다면 노드가 단 하나이니, node 반환함
            int mid = search(in,start,end,node.data);
            //인오더 결과방에서 해당 노드의 데이터를 가진 방번호 획득
            //그 방에서 왼쪽은 왼쪽트리, 오른쪽은 오른쪽 트리
            node.left = buildTreeByInPre(in, pre, start, mid-1);
            node.right = buildTreeByInPre(in, pre, mid+1, end);
            return node;
        }
       
        //postorder와 InOder
        public void buildTreeByInPost(int in[], int post[]){
            pIndex = post.length -1; //post데이터에서 하나씩 읽어옴 : root 맨 끝 
            root = buildTreeByInPost(in, post, 0 ,in.length -1);
        }

        private Node buildTreeByInPost(int[] in, int[] post, int start, int end) {
            if(start>end) return null; //크기가 적절하지 않으면 null만환
            Node node = new Node(post[pIndex--]); //pindex를 한칸씩 앞으로
            if(start == end) return node; //해당 레인지에 데이터가 한개임 (반환)
            int mid = search(in, start, end, node.data);
            //뒤에서 부터 읽으면 오른쪽 왼쪽 순서
            node.right = buildTreeByInPost(in, post, mid+1, end);
            node.left = buildTreeByInPost(in, post, start, mid-1);
            return node;
        }

        private int search(int arr[], int start, int end, int value) {
            int i ;
            for(i = start; i<=end; i++){
                if(arr[i] == value) return i;
            }
            return i;
        }

        void printInorder(Node node){
            if(node == null) return;
            printInorder(node.left);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        TreeRecreate treeRecreate = new TreeRecreate();
        TreeRecreate.Tree t = treeRecreate.new Tree();
        int[] pre = {4,2,1,3,6,5,7};
        int[] in = {1,2,3,4,5,6,7};
        int[] post = {1,3,2,5,7,6,4};
        t.buildTreeByInPre(in, pre);
        t.printInorder(t.root);
        t.buildTreeByInPost(in, post);
    }

    
}