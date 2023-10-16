public class LinkedListBack {

    /*
     * 문제 : 단방향 Linked List 의 끝에서 k번째 노드를 찾는 알고리즘을 구현하시오. 
     */

    public static void main(String[] args) {
    }

    /* Node */
    static class Node {
        Node next;
        int data;
        Node(int data) {
            this.data = data;
        }
    }

    /* 단방향 뒤에서 부터 세기
     * 전체 길이를 알아 낸 후 (k-1) + 1
     */
    static Node getNodeReadLength(Node first, int k) {

        Node n = first;
        int total = 1; //토탈 숫자. 마지막 노드에 가지 않으니 1에서 시작
        while(n.next != null){
            total++;
            n = n.next;
        }
    
        n = first; 
        for(int i =1; i<total-k+1; i++){
            n = n.next; 
        }
    
        return n;
    }

    /* printNodeFromRear - 재귀 + 출력으로 해결하는 방법 */
    static int printNodeFromRear(Node head, int k) {
        if(head == null) return 0;
        int index = printNodeFromRear(head.next, k) + 1; 
        //n의 next 값을 k와 같이 호출하여 + 1 (k와 값 비교) 
        if(index == k) { //같다면
            System.out.println(head.data);
        }
        return index; 
    }
    /* 위 방법은 int 값을 반환 노드를 반환해야함.
     * 객체에 해당 노드를 넣어서 반환하여 주소를 전달하자.
     */

    /* getNodeFromRearB, 객체 이용 */
    class Reference{
        public int count = 0;
    }
   
    static Node getNodeFromRearB(Node head, int k,Reference r) {
        if(head == null) return null; //r을 반환할 것.
        Node found = getNodeFromRearB(head.next, k, r) ;
        r.count++;
        if(r.count == k) {
            return head;
        }
        return found;
    }


    /* getNodepointer - 공간을 사용하지 않는 방법
     * 포인터 두개를 선언 후, 한개(p1)를 k만큼 앞으로 보냄
     * (p1,p2)한칸 씩 이동하여 끝의 null을 만나게 될 경우 p2가 k값이 됨.
     */
    static Node getNodePointer(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        for(int i=0; i<k; i++) {
            if(p1 == null) return null;
            p1 = p1.next;
        }

        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        System.out.println(p2);
        return p2;
    }

}



