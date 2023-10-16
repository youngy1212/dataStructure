public class Partition {

    /*
     * 조건 : Linked List에 있는 노드들을 x값을 
     * 기준으로 값이 작은 것들은 왼쪽, 큰 쪽은 오른쪽 두 파트로 나누시오. (단 x는 오른쪽 파트 어디에 놓아도 상관없음.
     */

    class Node {
        Node next;
        int data;
    }


    public static void main (String[] args){
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(4);
        ll.append(3);
        ll.append(4);
        ll.append(2);
        ll.append(8);
        ll.retrieve();
    
        //Node n = PartitionA(ll.get(1),5);
        while (n.next != null) {
            System.out.println(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data + "->");
    }

    //방법1 : 4 포인터를 이용한 방법
    private static Node PartitionA(Node n, int x){
        Node s1 = null;
        Node e1 = null;
        Node s2 = null;
        Node e2 = null;

        while(n != null){ //맨 마지막 노드까지
            Node next = n.next;
            n.next = null;
            if(n.data < x ){
                if(s1 == null){ //값이 아무것도 없으니 초기화
                    s1 = n;
                    e1 = s1;
                }else{
                    e1.next = n; //e1의 다음값을 n으로 넣어주고
                    e1 = n; //마지막은 n이 되도록 해주자.
                }
            }else{ //클때
                if(s2 ==null){
                    s2 = n;
                    e2 = s2;
                }else{
                    e2.next = n;
                    e2 = n;
                }
            }
            n = next; //노드의 마지막까지 반복
        }
        if(s1 == null){ //앞줄에 아무것도 없는 경우우
            return s2; 
        }

        e1.next = s2;
        return s1;

    }


    //방법2 : 앞 뒤로 붙이기 
    private static Node PartitionB(Node n, int x){
        Node head = n;
        Node tail = n;

        while(n!= null){
            Node next = n.next;
            if(n.data <x){ //head 앞으로 붙이기. 원래 haed를 뒤로 붙이고 교체!
                n.next = head;
                head = n;
            }else{ //크거나 같은경우 tail에 붙이기
                tail.next = n;
                tail = n;
            }
            n = next; //마지막까지 반복
        }
        tail.next = null; //마지막임을 표현하기 위해 비워주기

        return head;

    }


    
}
