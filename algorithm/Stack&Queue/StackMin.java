
import java.util.Stack;

import javax.print.DocFlavor.STRING;

class NodeWithMin{ //노드에 값과 작은값 두개 선언
    int value;
    int min;

    NodeWithMin(int v, int min){
        value = v;
        this.min = min;
    }
}

class StackWithMin extends Stack<NodeWithMin>{
    public int min(){
        if(this.isEmpty()){
            return Integer.MAX_VALUE; //정수값 가장큰값. 그래야 다음에 이것보다 작으니까
            //자기값으로 바꿔서 저장될것
        }else{
            return peek().min;
        }    
    }

    public void push(int value){ //작은값을 저장
        int newMin = Math.min(value, min());
        super.push(new NodeWithMin(value, newMin));
    }
}

//2. Stack 을 이용
class StackWithMin2 extends Stack<Integer>{
    Stack<Integer> s2; //작은값을 저장할 스택
    public StackWithMin2(){
        s2= new Stack<Integer>();
    }

    public int min(){
        if(s2.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            return s2.peek();
        }
    }

    public void push(int value){ //push할 값고 작은값 탑의 값을 비교
        if(value < min()){
            s2.push(value); //새로운값 넣기
        }
        super.push(value);
    }

    public Integer pop(){
        int value = super.pop();
        if(value == min()){ //자기랑 같은지 비교
            s2.pop(); //스택에서 함께 삭제
        }
        return value;
    }

}

public class StackMin {
    //stack을 구현하는데 push와 pop과 더불어 가장 작은 값을 반환하는 
    //min 함수를 같이 구현하시오(단, 모든 함수들은 O(1) 의 시간이 걸려야함. )
    public static void main(String[] args){
        //StackWithMin stack = new StackWithMin();
        StackWithMin2 stack = new StackWithMin2();
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(2);
        System.out.println("min:"+stack.min());
        System.out.println("pop:"+stack.pop());
        System.out.println("min:"+stack.min());
        System.out.println("pop:"+stack.pop());
        System.out.println("min:"+stack.min());
        
    }
}
