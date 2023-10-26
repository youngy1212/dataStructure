
/*
 *      (1)
 *   (2)   (3)
 * (4) (5)
 * Inorder (Left, Root, Right) : 4 2 5 1 3
 * Preorder (Root, Left, Right) : 1 2 4 5 3
 * Postorder (Left, Right, Root) : 4 5 2 3 1 
 */


class Node{
    int data;
    Node left;
    Node right;
}

class Tree{

    public Node root;

    public void setRoot(Node node){
        this.root = node;
    }

    public Node getRoot(){
        return root;
    }

    public Node makeNode(Node left, int data, Node right){
        Node node = new Node();
        node.data = data;
        node.left = left;
        node.right = right;

        return node;
    }

    public void inorder(Node node){
        //노드가 null이 아닐때까지 재귀호출 반복
        if(node != null){
            inorder(node.left); //왼쪽을 다돌면
            System.out.println(node.data); //나 호출
            inorder(node.right); //오른쪽 호출
        }
    }

    public void preorder(Node node){
        if(node != null){
            System.out.println(node.data); //나 호출
            preorder(node.left); //왼쪽을 다돌면    
            preorder(node.right); //오른쪽 호출
        }
    }
    public void postorder(Node node){
        if(node != null){
            postorder(node.left); //왼쪽을 다돌면    
            postorder(node.right); //오른쪽 호출
            System.out.println(node.data); //나 호출
        }
    }



    


}



public class BinaryTreeCyclic{
    public static void main(String[] args) {

        Tree t =new Tree();
        Node n4 = t.makeNode(null, 4, null);
        Node n5 = t.makeNode(null, 5, null);
        Node n2 = t.makeNode(n4, 2, n5);
        Node n3 = t.makeNode(null, 3, null);
        Node n1 = t.makeNode(n2, 1, n3);
        t.setRoot(n1); //1번노드를 root로 지정
        t.postorder(t.getRoot());

    }
}