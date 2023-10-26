
// Stack에 데이터를 쌓다가 어느 지점에 이르면 새로운 스택에 저장하는 식으로 SetOfStack을 구현하시오
// → 내부적으로는 여러개의 스택으로 나뉘지만, push와 pop 여전히 하나의 스택인것처럼 동작해야함. 
// → 세트 중 하나의 stack을 지정해서 데이터를 꺼내올 수 있는 popAt() 함수를 만들기

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.lang.Exception;


class FullStackException extends Exception {
    FullStackException(){
        super();
    }
}

class EmptyStackSetException extends Exception {
    EmptyStackSetException(){
        super();
    }
}




//여러개의 스택 
//class SetOfStack{

    // int capacity; //얼마나 쌓였는지 확인
    // ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();

    // SetOfStack(int capacity) { //얼마나 높게 쌓았는지
    //     this.capacity = capacity;
    // }

    // //마지막 스택을 가져오는 함수
    // public Stack<Integer> getLastStack(){
    //     if(stacks.size() == 0) return null;
    //     return stacks.get(stacks.size()-1);
    // }

    // //스택추가
    // public void addStack(){
    //     stacks.add(new Stack<Integer>());
    // }

    // //삭제
    // public void removeLastStack(){
    //     stacks.remove(stacks.size() -1); //마지막 삭제
    // }

    // //데이터 추가
    // public void push(int data){
    //     Stack<Integer> last = getLastStack(); //마지막 스택의 포인터 가져오기
    //     if(last == null || last.size() == capacity){//비었거나, 마지막이라면
    //         addStack(); //스택을 추가하고
    //         last = getLastStack(); //마지막 주소를 다시받아옴.
    //     }

    //     last.push(data);
    // }

    // public int pop(){
    //     Stack<Integer> last = getLastStack();
    //     if(last == null || last.isEmpty()){ //비엇는지 확인
    //         throw new EmptyStackException();
    //     }

    //     int data = last.pop();
    //     if(last.isEmpty()){
    //         removeLastStack();;
    //     }

    //     return data;
    // }


//}

//2. 임의의 stack에서 pop() 
//스택구현
class Stack<E>{
    class Node{
        E data;
        Node below; //위아래 노드
        Node above;

        Node(E data){
            this.data = data;
        }
    }

    //멤버 변수로 선언
    int capacity;
    int size;
    Node top;
    Node bottom;
    
    Stack(int capacity){
        this.capacity = capacity;
    }

    public boolean isEmpty() {return size == 0;}
    public boolean isFull() {return size == capacity;}
    public int size() {return size;} //데이터가 얼만큼 쌓였는지

    public void push(E d) throws FullStackException{
        if(isFull())throw new FullStackException(); //한계점이면 오류

    
        Node n = new Node(d);
        push(n);
    }

    //노드를 받는 push
    public void push(Node n) throws FullStackException{
        if(isFull())throw new FullStackException(); //한계점이면 오류

        if(isEmpty()){ //가장 처음
            top = n;
            bottom = n;
        }else{
            top.above = n;
            n.below = top; 
            top = n; //새로운 노드 top
        }
        size++;
    }

    public E pop(){
        if(isEmpty()) throw new EmptyStackException();
        E data = top.data; //탑데이터
        top = top.below; //밑에있는걸 탑으로 변경
        if(top == null){ //변경한 탑 null
            bottom = null; 
        }else{
            top.above = null; //변경된 top이 가지고있던 윗 노드 삭제
        }
        size--;
        return data;
    }
    //밑에서 뺴오기
    public Node popBottom(){
        if(isEmpty()) throw new EmptyStackException();
        Node n = bottom; //바텀노드 획득
        bottom = bottom.above; //위에 노드를 바텀으로 변경
        if(bottom == null){ //스택에 데이터가 없는 것 
            top = null; //탑도 null
        }else{
            bottom.below = null; //바텀 null; 
        }
        size--;
        return n;
    }

}

class SetOfStack2{
    int capacity; //스택이 가질수 있는 노드갯수
    ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();

    SetOfStack2(int capacity){
        this.capacity = capacity;
    }

    public void addStack(){ //스택 생성 + arrayList 추가
        stacks.add(new Stack<Integer>(capacity));
    }
    public void removeLastStack(){
        stacks.remove(stacks.size()-1);
    }
    public Stack<Integer> getLastStack(){
        if(stacks.size() == 0) return null;
        return stacks.get(stacks.size()-1);
    }

    public void push(int data){
        Stack<Integer> last = getLastStack();
        if(last == null || last.isFull()){
            addStack();
            last = getLastStack();
        }
        try {last.push(data);} catch (FullStackException e){}
    }

    public int pop(){
        Stack<Integer> last = getLastStack();
        if(last == null || last.isEmpty()) throw new EmptyStackException();
        int data = last.pop();
        if(last.isEmpty()) removeLastStack(); //비엇는지 체크하고 스택 삭제
        return data;
    }

    public int popAt(int index){
        if(stacks.size() == 0){ //스택이 하나도 없다면
            throw new EmptyStackException();
        }
        if(index <0 || index>=stacks.size()){
            throw new IndexOutOfBoundsException(); 
        }

        Stack<Integer> stack = stacks.get(index);
        if(stack == null || stack.isEmpty())throw new EmptyStackException();
        //비어있다면 예외처리

        int data = stack.pop(); //아니면 데이터 가져오기 
        shiftLeft(index);
        return data;
    }

    public void shiftLeft(int index){ //index 전까지 위치이동
        if(index < stacks.size()-1){
            Stack<Integer> right = stacks.get(index+1);
            Stack<Integer> left = stacks.get(index);

            try {
                left.push(right.popBottom()); //왼쪽스택에 오른쪽 b를 넣어주기
            } catch (FullStackException e) {}
            if(right.isEmpty()){ //그 뒤 스택이 비었다면 
                stacks.remove(index+1);
            }
            shiftLeft(index+1);
        }

    }


}



public class SetStack {

    public static void main(String[] args) {

        SetOfStack2 sos = new SetOfStack2(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);

        sos.push(4);
        sos.push(5);
        sos.push(6);

        sos.push(7);

        System.out.println(sos.popAt(0));
        System.out.println(sos.popAt(1));
        //System.out.println(sos.popAt(3));

    }
    
}