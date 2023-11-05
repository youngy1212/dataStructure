class BinarySearchTree {

    class Node{ 
        int data;
        Node left, right;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    public Node search(Node root, int key){
        if(root == null || root.data == key) return root; //트리를 돌다가 찾는데이터를 만나거나 null까지 왔다면
        if(root.data > key) return search(root.left, key); //아직 못찻았으면 node값과 key를 비교 작다면 왼쪽 root 
        return search(root.right, key); //크다면 오른쪽 root
    }
    public void insert(int data){ //insert할 함수
        root = insert(root,data);  //결과를 받아서 결과에 저장
    }
    private Node insert(Node root, int data) { //루트와 insert할 data
        if(root == null){
            root = new Node(data); //노드를 생성해서 반환
            return root;
        }

        if(data < root.data){ //데이터가 작으면 (왼쪽)
            root.left = insert(root.left, data);
        }else if(data > root.data){ //크면 오른쪽 
            root.right = insert(root.right, data);
        } //돌다가 null나오면 새로 노드만드는것.
        
        return root;
    }
    public void delete(int data){
        root = delete(root,data); //재귀호출 
    }
    private Node delete(Node root, int data){ 
        if(root == null ) return root; //노드가 널 
        if(data < root.data){ //작으면 왼
            root.left = delete(root.left, data);
        }else if(data > root.data){ //오 
            root.right = delete(root.right, data);
        }else{ //삭제할 데이터를 찾음 (세가지 케이스)
            if(root.left == null && root.right == null) return null; //자식이 없는 경우, null반환 끊어져서 삭제
            else if(root.left == null ) return root.right;  // 자식을 위로 올림, 
            else if(root.right == null) return root.left; 

            //자식이 둘다 있는 경우  (오른쪽 노드에서 파생된 가장 작은 값을 찾음)
            root.data = findMin(root.right);
            root.right = delete(root.right, root.data);
        } 
        return root;
    }
    int findMin(Node right) { //root받아서 왼쪽에 null을 만날때까지 (가장 작은값)
        int min = root.data;
        while (right.left != null) {
            min = root.left.data;
            root = root.left;
        };
        return min;
    }

    //테스트용 order
    public void inorder(){
        inorder(root);
        System.err.println("");
    }
    private void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.err.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(7);
        tree.inorder();
        tree.delete(3);
        tree.inorder();

    }
}


