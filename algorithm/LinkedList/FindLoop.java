
public class FindLoop {

    public static void main(String[] args){
        Node n1 = new Node(1);
        Node n2 = n1.addNext(2);
        Node n3 = n2.addNext(3);
        Node n4 = n3.addNext(4);
        Node n5 = n4.addNext(5);
        Node n6 = n5.addNext(6);
        Node n7 = n5.addNext(7);
        Node n8 = n5.addNext(8);

        n8.addNext(n4); //루프

        Node n = findLoop(n1);

        if(n != null){
           System.out.println("strat of loop : "+n.data);
        }else{
           System.out.println("Loop not found");
        }

    }

    private static Node findLoop (Node head){
        Node fast = head; //토끼
        Node slow = head;   //거북이

        while(fast != null && fast.next != null){
            //fast가 계속돈다.
            slow = slow.next;
            fast = fast.next.next; //두칸씩
            
            if(fast == slow){
                break; //만나면 정지!
            }
        }

        if(fast == null && fast.next == null){ //만나지못한다면 그냥 종료
            return null;
        }

        slow = head; //거북이를 처음으로 이동

        //거북이와 도끼가 만날때까지! 
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;

        }

        return fast;

    }

}

