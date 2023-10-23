import java.util.NoSuchElementException;

class Queue<T> {
    
    //같은 타입을 받는 노드 선언
    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }

    //큐는 앞뒤로 주소를 알아야함.
    private Node<T> first;
    private Node<T> last;


    //추가하기
    public void add(T item){
        Node<T> t = new Node<T>(item);

        if(last != null){ //마지막 노드가 있다면
            last.next = t; //새로생성한 노드 붙여주기
        }
        last = t;
        if(first == null){
            first = last; //첫 번째 노드라면
        }
    }

    //지우기
    public T remove(){
        if(first == null){
            throw new NoSuchElementException();
        }

        T data = first.data;
        first = first.next; 

        if(first == null){
            last = null;
        }
        return data;
    }

    public T peek(){
        if(first == null){
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty(){
        return first == null;
    }


}


public class QueueTest {
    public static void main(String[] args){
        Queue<Integer> q = new Queue<Integer>();

        //담기
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.peek());
        System.out.println(q.isEmpty());
        System.out.println(q.remove());

    }
}
