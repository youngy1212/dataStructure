import java.util.Random;

public class RandomNode {
    class Tree{
        class Node{ 
            int data;
            Node left;
            Node right;
            int size; //하위노드 개수
            Node(int data){
                this.data = data;
                this.size = 1;
            }
            void insert(int data){ //트리의 insert할떄 nodesize 증가
                if(data <= this.data){ //작다면 왼쪽에 추가
                    if(left == null){
                        left = new Node(data);
                    }else{
                        left.insert(data);
                    }
                }else{ //크면 오른쪽에 추가
                    if(right == null){
                        right = new Node(data);
                    }else{
                        right.insert(data);
                    }
                }
                size++; //사이즈를 늘려줌 
            }
            int size(){return size;}
            Node find(int data){
                if(data == this.data){
                    return this;
                }else if(data <this.data){
                    return left != null ? left.find(data) : null;
                }else if(data > this.data){
                    return right != null? right.find(data) : null;
                }else{
                    return null;
                }
            }
            //해당 노드를 찾아서 반환
            Node getIthNode(int i){
                int leftSize = left == null ? 0 : left.size();
                if(i<leftSize){ //찾는 수가 왼쪽노드수보다 작다면
                    return left.getIthNode(i);//왼쪾에 있음
                }else if(i == leftSize){
                    return this;
                }else{
                    return right.getIthNode(i-(leftSize + 1)); //오른쪽에 있음, 왼쪽+자신뺴야 오른족에서 새롭게 검색
                }
            }
        }

        Node root;
        

        int size(){
            return root == null ? 0 : root.size();
        }

        void insert(int data){
            if(root == null) root = new Node(data);
            else root.insert(data);
        }

        Node getRandomNode(){
            if(root == null) return null;
            Random random = new Random();
            int i = random.nextInt(size());
            return root.getIthNode(i);
        }
    
    }

    public static void main(String[] args) {
        RandomNode randomNode = new RandomNode();
        RandomNode.Tree t = randomNode.new Tree();
        t.insert(4);
        t.insert(0);
        t.insert(1);
        t.insert(2);
        t.insert(5);
        t.insert(7);
        t.insert(8);
        t.insert(3);
        t.insert(6);
        t.insert(9);
        System.out.println( t.getRandomNode().data);

    }
}   
