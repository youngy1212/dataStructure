import java.util.*;

public class CommonParents {

class Tree{
    class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
        }
    } 
    Node root;
    HashMap<Integer, Node> rootMap;
    Tree(int size){
        rootMap = new HashMap<Integer,Node>();
        root = makeBST(0,size-1,null);
    }

    Node makeBST(int start, int end, Node parent){
            if(start > end) return null;
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start,mid-1,node);
            node.right = makeBST(mid+1, end,node);
            node.parent = parent;
            rootMap.put(mid, node); //값으로 node를 찾아올 수 있도록 map에 노드등록
            return node;
        }

        Node getNode(int data){
            return rootMap.get(data);
        }

        Node commonAncestor(int d1, int d2){ //두개의 값을 받아서 
            Node p = getNode(d1);
            Node q = getNode(d2);
            int diff = depth(p) - depth(q); //root부터 node까지의 길이 
            Node first = diff > 0? q:p;
            Node second = diff > 0? p:q;
            second = gotUpBy(second, Math.abs(diff)); //길이를 맞춰주는 함수 호출
            while (first != second && first!=null &&second!=null) { //함께위로 한칸씩   
                first = first.parent;
                second = second.parent;
            }

            //만나는 지점 
            return first == null || second == null ? null : first;
        }

        Node gotUpBy(Node node, int diff){
            while (diff>0 && node != null) {
                node = node.parent;
                diff--;
            }
            return node;
        }

        int depth(Node node) {
            int depth = 0;
            while (node != null) {
                node = node.parent;
                depth++;
            }
            return depth;
        }

        //방법2 
        boolean covers(Node root, Node node){ //인자로 받은 노드가 자손인지
            if(root == null) return false;
            if(root == node)return true;
            return covers(root.left, node)||covers(root.right, node);
        }
        Node commonAncestor2(int d1, int d2){ //공통부모를 찾는 함수
            Node p = getNode(d1);
            Node q = getNode(d2); 
            if(!covers(root, p)|| !covers(root, q)){ 
                return null;
            }else if(covers(p, q)){ //p가 q의 부모노드라면
                return p;
            }else if(covers(q, p)){
                return q;
            }
            Node sibling = getSibling(p); //현재노드를 가져오기
            Node parent = p.parent;
            while (!covers(sibling, q)){ //레벨을 한칸씩 올라가며 2번쨰 노드가 
                sibling = getSibling(parent);   //형제의 하위트리 노드에 있는지 체크
                parent = parent.parent;
            }
            return parent;
        }

        //내가 부모의 오른쪽 자식이면 왼쪽을 왼쪽이면 오른쪽을 
        Node getSibling(Node node){
            if(node == null || node.parent == null){
                return null;
            }

            Node parent = node.parent;
            return parent.left == node? parent.right : parent.left;
        }

        //방법3 : 노드안에 부모정보가 없음
        Node commonAncestor3(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            if(!covers(root, p) || !covers(root, q)){
                return null; //p나 q가 트리안에 존재하는지 체크
            }
            return ancestorHelper(root, p, q); //재귀함수 호출
        }
        Node ancestorHelper(Node root, Node p, Node q){
            if(root == null || root == p || root == q){//끝까지오거나 찾았다면
                return root; //해당 노드반환
            }
            boolean pIsOnLeft = covers(root.left, p); //p가 왼쪾트리에 있는지 확인
            boolean qIsOnLeft = covers(root.left, q); //q가 왼쪽트리에 있는지 확인
            //왼쪽에 없다면 오른쪽 에 존재하는 뜻
            if(pIsOnLeft != qIsOnLeft){ //다른방향
                return root;
            }
            Node childSide = pIsOnLeft? root.left : root.right;//해당 방향으로 다시 내려가 검사
            return ancestorHelper(childSide, p, q);
        }

        //방법4 :  Postorder
        Node commonAncestor4(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            return commonAncestor4(root, p, q); //재귀함수 호출
        }

        Node commonAncestor4(Node root, Node p, Node q){
            if(root == null) return null;
            if(root == p && root == q) return root; //둘다 같으면 본인이 공통노드
            Node x = commonAncestor4(root.left, p ,q); //왼쪾먼저 검색
            if(x != null && x != p && x!= q){ //노드가 반환됐는데 p도 q도아니야. 
                //아래서 공통노드를 찾아서 반환해준것
                return x;
            }
            Node y = commonAncestor4(root.right, p, q); //오른족 
            if(y != null && y != p && y != q){ 
                return y;
            }

            //순서 매우 중요 
            if(x != null && y != null){//1. 노드를 하나씩 찾아온것 q와 p
                return root;
            }else if(root == p || root == q){ //2. 해당 노드가 찾는 노드일 경우
                return root; //p와 q가 부모자식일 경우 찾으면 올라가 버릴까바 
                //p먼저 찾고 q찾음
            }else{ //3. 자식이 찾인 null일수도 아닐수도 있는 값을 전달 
                return x == null ? y : x;
            }
        }

        //방법 5. 정확한 결과를 반환
        class Result { //결과 반환
            Node node;
            boolean isAncestor;
            Result(Node n, boolean isAnc){
                node = n;
                isAncestor = isAnc;
            }
        }

        Node commonAncestor5(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            Result  r = commonAncestor5(root, p, q);
            if(r.isAncestor){
                return r.node;
            }
            return null;
        }


        Result commonAncestor5(Node root, Node p, Node q){
            if(root == null) return new Result(null, false);
            if(root == p && root == q) return new Result(root, false);

            Result rx = commonAncestor5(root.left, p ,q); 
            if(rx.isAncestor) return rx;
            
            Result ry = commonAncestor5(root.right, p, q); 
            if(ry.isAncestor) return ry;

            //순서 매우 중요 
            if(rx != null && ry != null){
                return new Result(root, true);
            }else if(root == p || root == q){ //2. 해당 노드가 찾는 노드일 경우
                boolean isAncestor = rx.node != null || ry.node != null;
                return new Result(root, isAncestor);
                //p와 q중에 결과를 찾았는데 이미 노드에 들어갔다면 이 노드가 
                //찾는 동시에 공통 노드가 되는 것 
            }else{ //3. 자식이 찾인 null일수도 아닐수도 있는 값을 전달 
                return new Result(rx.node != null ? rx.node: ry.node, false);
            }
        }


}
    

    public static void main(String[] args) {
        CommonParents commonParents = new CommonParents();
        Tree t = commonParents.new Tree(10);
        Tree.Node fa = t.commonAncestor5(6, 8);
        System.out.println(fa.data);
        
    }
}
