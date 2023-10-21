
public class Linked_List_Digit {
    
    class Node {
        Node next;
        int data;
    }

    //재귀 호출을 할때 사용할 클래스 선언
    class Storage{
        int carry = 0;
        Node result = null;
    }


    public static void main(String[] args){
        LinkedList l1 = new LinkedList();
        l1.append(9);
        l1.append(1);
        l1.append(4);
        l1.retrieve();

        LinkedList l2 = new LinkedList();
        l1.append(6);
        l1.append(4);
        l1.append(3);
        l1.retrieve();

        Node n = sumLists(l1.get(1), l2.get(1),0);
        while(n.next!= null){
                System.out.println(n.data + "->");
                n = n.next;
        }
        System.out.println(n.data);
        
    }

    //방법 1 : 뒤(일)의자리 부터 호출
    private static Node sumLists(Node l1, Node l2, int carry){

        if(l1 == null && l2 == null && carry == null){
            return null;
        }

        Node result = new Node();
        int value = carry;

        if(l1 != null ){
            value += l1.data;
        }
        if(l2 != null){

            value += l2.data;
        }

        result.data = value % 10;

        if(l1 != null || l2 != null){
            Node next = sumLists(l1 == null ? null: l1.next, 
            l2 == null ? null: l1.next, value >= 10? 1:0);
            result.next = next; //결과값을 받아와서 연결
        }

        return result;
    }

    //방법2 : 앞에서 부터 호출
    private static Node sumLists2(Node l1, Node l2){
        int len1 = getListLen(l1);
        int len2 = getListLen(l2);
        //list의 길이 받아오기

        //0으로 채워주기
        if(len1 < len2){ //차이만큼 0으로 채워라
            l1 = LPadList(l2, len2-len1);
        }else{
            l2 = LPadList(l2,len1-len2 );
        }

        Storage storage = addLists(l1,l2);
        if(storage.carry != 0){ //다돌았을때 carry가 있는지 체크해야함.
            storage.result = insertBefore(storage.result, storage.carry);
        }
        
        return storage.result;
    
    }

    //정리된 list를 계산해주기
    private static Storage addLists(Node l1, Node l2){
        
        //돌다가 둘다 null이되면 (끝)
        if(l1 == null && l2 == null){
            Storage storage = new Storage();
            return storage;
        }
        Storage storage = addLists(l1.next, l2.next); //재귀호출
        
        //계산할 공간 캐리가 있는지 확인
        int value = storage.carry + l1.data + l2.data;
        int data = value % 10; //저장해야할 값
        storage.result =insertBefore(storage.result, data);
        //저장 앞에 붙여라, 다시 저장
        storage.carry = value /10; //몫
        return storage;

        //돌면서 계속 storage result에 값을 추가해줌.
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

    //Node 앞에 새로운 노드를 추가하는 기능 
    private static Node insertBefore(Node node, int data){
        Node before = new Node(data);
        //받은 데이터 뒤에 붙어줌
        if(node != null){
            before.next = node;
        }

        return before;
    }

    //왼쪽에 0을 채워주는 함수
    private static Node LPadList(Node l, int length){
        Node head = l;
        for(int i =0; i<length; i++){
            head = insertBefore(head, 0); //헤드앞에 0을 붙여라
        }

        return head;
    }

}
