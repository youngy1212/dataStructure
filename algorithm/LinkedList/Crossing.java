public class Crossing {
    
    static class Node {
        int data;
        Node next = null;
        
    }

    public static void main (String[] args){
        Node n1 = new Node(5);
        Node n2 = n1.addNext(7);
        Node n3 = n2.addNext(9);
        Node n4 = n3.addNext(10);
        Node n5 = n4.addNext(7);
        Node n6 = n5.addNext(6);

        Node m1 = new Node(6);
        Node m2 = m1.addNext(8);
        Node m3 = m2.addNext(n4); //n4에서 교체
        
        Node n = getIntersection(n1, m1);

        if(n != null){ //널이 아니라면 교차점 출력
            System.out.println("Intersection:" +n.data);
        }else{
            System.out.println("Not found");
        }


    }

    private static Node getIntersection (Node l1, Node l2){


        //두개의 길이를 알아야함.
        int len1 = getListLen(l1);
        int len2 = getListLen(l2);

        //시작점을 찾기
        if(len1>len2){
            l1 = l1.get(len1 - len2);
            //시작점을 len2 시작지점부터 가져와라.
        }else if(len1 < len2){
            l2 = l2.get(len2 = len1);
        }

        //앞에서 부터 비교
        while(l1 != null && l2 != null){
            if(l1 == l2){
                return l1; //같아지면 해당 노드 리턴
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        //그래도 못찾았다면 null 없음
        return null;

    }
}
