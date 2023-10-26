import java.util.Stack;

class MyQueue<T>{
    Stack<T> stackNewest, stackOlddest; //두개의 스택

    MyQueue(){
        stackNewest = new Stack<T>();
        stackOlddest =new Stack<T>();
    }

    public int size(){
        return stackNewest.size() + stackOlddest.size();
    }

    //데이터 추가
    public void add(T value){
        stackNewest.push(value);
    }

    //old 스택으로 넘겨주기
    public void shiftStacks(){
        if(stackOlddest.isEmpty()){
            while(!stackNewest.isEmpty()){
                stackOlddest.push(stackNewest.pop());
            }
            //stackNewest 빌때까지 stackNewest로 이동
        }
    }

    //oldstack에서 보고, 삭제
    public T peek(){
        shiftStacks();
        return stackOlddest.peek();
    }

    public T remove(){
        shiftStacks();
        return stackOlddest.pop();
    }



}




public class TwoStackQueue {
    
    public static void main(String[] args) {

        MyQueue<Integer> q = new MyQueue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        
    }
}
