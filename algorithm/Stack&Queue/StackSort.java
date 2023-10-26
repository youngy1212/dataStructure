import java.util.Stack;



public class StackSort {
    
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(3);
        s1.push(5);
        s1.push(1);
        s1.push(6);

        sort(s1);
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());

    }

    //스택을 인자로 받는 sort함수 
    public static void sort(Stack<Integer> s1){

        //임시로 사용할 두번째 스택
        Stack<Integer> s2 = new Stack<Integer>();
        while(!s1.isEmpty()){
            int tmp = s1.pop(); //비교할 수
            while(!s2.isEmpty() && s2.peek()>tmp){ //데이터가 pop해 온것보다 크다?
                s1.push(s2.pop()); //s1로 옮져준다.
            }
            s2.push(tmp); //작아진다면 임시로 저장했던 tmp을 push
        }

        while(!s2.isEmpty()){ //장렬된 스택이 들어있음
            s1.push(s2.pop());
        }
    }
}
