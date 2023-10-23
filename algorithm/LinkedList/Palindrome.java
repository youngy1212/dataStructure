import java.util.*;

public class Palindrome {
    
    Node header;

    static class Node {
        int data;
        Node next = null;
        
    }


    //저장할 객체 선언
    class Storage{
        public Node node; //위치를 저장할 노드
        public boolean result; //각 값이 같은지 저장할

        Storage (Node n, boolean r){
            node = n;
            result = r;
        }
    }



    public static void main (String[] args){
        LinkedList ll = new LinkedList();
        ll.append('m');
        ll.append('a');
        ll.append('d');
        ll.append('a');
        ll.append('m');
        ll.retrieve();

        isPalindrome(ll.get(1));
    
    }

    //첫번쨰 노드 인자로 받음, 해당 노드를 리버스하여 저장.
    private static boolean isPalindrome(Node head){
        Node reversed = reverseAndClone(head);
        return isEqual(head,reversed);
    }

    //반대로 정렬하는 함수
    private static Node reverseAndClone(Node node){
        Node head = null;
        //돌면서 정렬
        while(node != null){
            Node n = new Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }

        return head;
    }


    //두개가 같은지 비교
    private static boolean isEqual(Node one, Node two){
        while(one != null && two != null){
           
           if(one.data != two.data){
                return false;
           }

           one = one.next;
           two = two.next;

           //한칸씩 옮기며 비교
            
        }

        return one == null && two == null;
        
    }


    //방법2: 토끼와 거북이 포인터2개를 이용
    private static boolean isPalindrome2(Node head){
        //두개의 포인터 선언
        Node fast = head;
        Node slow = head;

        //호리병 : 스택 선언
        Stack<Character> stack = new Stack<Character>();

        //토끼가 fast가 마지막 노드에 도착할때까지 반복문
        while(fast != null && fast.next != null){
            //거북이가 slow가 스택에 모음
           stack.push(slow.data);
           slow = slow.next;
           fast = fast.next.next;
        }

        if(fast != null){
            slow = slow.next;
        }

        //끝까지 왔다면 스택에 쌓인값과 거북이위치부터 비교
        while(slow != null){
            char c = stack.pop();

            //문자가 같지않다면
            if(slow.data != c){
                return false; //바로 false 리턴
            }

            slow = slow.next; //한칸이동

        }

        return true;
    }


    //3. 재귀 호출을 이용한 방법
    private static boolean isPalindrome3(Node head){
        int length = getListLen(head);
        //
        Storage storage = isPalindromeRecursive(head,length);
        return storage.result; //저장된 결과값을 반환환
    }

    //재귀함수
    private static Storage isPalindromeRecursive(Node head, int length) {
        
        if(head == null || length <= 0 ){//만약 노드의 끝까지 오거나, list에 중앙에 왔다면
            return new Storage(head, true); //임시 저장 Storage
        }else if(length == 1){ //홀수개인 경우
            return new Storage(head.next, true);
        }

        //다음 노드를 가지고 길이를 2줄여서 한번도 호출
        Storage storage = isPalindromeRecursive(head.next, length-2);
        
        if(!storage.result || storage.node == null){ //하나라도 false라면 반환
            return storage;
        }

        //현재 노드가 스토리지와 같다면
        storage.result = (head.data == storage.node.data);
        storage.node = storage.node.next;

        return storage;
    }

    //Node 길이를 가져오는 기능
    private static int getListLen(Node l){
        int total = 0;
        while (l != null){
            total++;
            l = l.next;
        }
        return total;
    }
}
