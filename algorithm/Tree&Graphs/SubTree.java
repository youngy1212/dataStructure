public class SubTree {

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
        
    }
    
    //T2가 t1에 포함되는지 확인
    boolean containsTree(Node t1, Node t2){

        //기본확인
        if(t2== null) return true;
        return subTree(t1,t2);
    }

    boolean subTree(Node t1, Node t2){
        if(t1 == null){
            return false;
        }else if(t1.data == t2.data && matchTree(t1,t2)){
            return true;
        }
        return subTree(t1.left, t2) || subTree(t1.right, t2); //계속해서 검색
    }

    boolean matchTree(Node t1, Node t2){
        if(t1 == null && t2 == null){ //서로 틀린걸 발견하지 못하고 끝까지왔다는 뜻
            return true;
        }else if(t1 == null || t2 ==null){ //같지 않다는 뜻이니 false
            return false;
        }else if(t1.data != t2.data ){
            return false;
        }else{
            return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
            //다시 왼쪽 오른쪽 비교
        }
    }

}
