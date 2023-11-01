public class NextNodeFind {
    class Tree{
        class Node{
            int data;
            Node left;
            Node right;
            Node parent; //부모까지 필요함
            Node(int data){
                this.data = data;
            }
        }
        Node root;

        Tree(int size){
            root = makeBST(0,size-1,null);
        }
        Node makeBST(int start, int end, Node parent){
            if(start > end) return null;
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start,mid-1,node);
            node.right = makeBST(mid+1, end,node);
            node.parent = parent;
            return node;
        }

        void findNext(Node node){
            if(node.right == null){ //오른쪽노드가 없는경우
                //위에서 찾아야함 부모+현재노드 그래야 내가 왼/오 어느쪽 자식인지 암
                System.out.println(findAbove(node.parent, node).data + "is"+ node.data+"'s next");
            }else{ //오른쪽 자식이 있으면 아래에서 찾음 
                System.out.println(findBelow(node.right).data + "is"+ node.data+"'s next");
            }
        }
        Node findAbove(Node root,Node child) {
            //부모 + 나자신
            if(root ==null) return null;
            if(root.left == child) return root; //왼쪽시 부모가 다음노드
            return findAbove(root.parent, root); //오른쪽이면 다시호출
        }
    
        Node findBelow(Node root) {
            if(root.left == null) return root; //왼쪽에 더이상 자식이 없을때 
            return findBelow(root.left);
        }
    
    }

    /*
     *             (4)
     *     (1)             (7)
     * (0)    (2)       (6)     (8)
     *          (3)        (6)     (9)
     */
    
    
    public static void main(String[] args) {
        NextNodeFind nextNodeFind = new NextNodeFind();
        Tree t = nextNodeFind.new Tree(10);
        t.findNext(t.root.left.right.right); //4 is 3's next
        t.findNext(t.root.left); //2 is 1's next
    }


   
}