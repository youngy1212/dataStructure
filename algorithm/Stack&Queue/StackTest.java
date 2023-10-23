import java.util.*;

class Stack<T> { //스택 class만들어주기
    class Node<T>{
        private T data; //데이터
        private Node<T> next; //다음노드

        public Node(T data){
            this.data = data; //생성자에서 받아 내부변수에 저장
        }
    }

    private Node<T> top; // 맨위에 올라가는 주소만 알면됨.


    //가장 위에있는 값을 가져오는 함수
    public T pop(){
        if(top == null){//top이 null이면
            throw new EmptyStackException(); //exption오류
        }

        T item = top.data;
        top = top.next;
        return item;
    }

    //스택에 넣어주는 함수
    public void push(T item){ //아이템을 받아서 노드를 생성
        Node<T> t = new Node<T>(item); 
        t.next = top; //top 앞에 위치시키고 
        top = t; //이노드가 t이됨.
    }

    //맨위값 불러오기
    public T peek(){
        if(top == null){ //null체크
            throw new EmptyStackException();
        }

        return top.data;

    }

    //빈값인지
    public boolean isEmpty(){
        return top == null;
    }


}

public class StackTest{
    public static void main(String[] args){

        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.isEmpty());
        System.out.println(s.pop());
        System.out.println(s.isEmpty());


    }
}
