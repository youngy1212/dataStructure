
class LinkedList {

    Node header;

    static class Node {
        int data;
        Node next = null;
        
    }

    LinkedList(){
        header = new Node();
    }

    //새로운 노드 생성
	void append(int d){ 
		Node end = new Node();
        end.data = d;
        Node n = header;
		while(n.next != null){ //맨마지막 노드
		 n = n.next;
	    }
	    n.next = end; //새로운 노드가 맨 마지막 노드
	}
	
	//노드 삭제
	void delete(int d){
		Node n = header; //아직 지워야할 값을 모름
		while(n.next != null){ //마지막까지 가지 않음. 이미 앞에서 판단
			if(n.next.data == d){ //삭제해야할 노드찾기 
					n.next = n.next.next; // 뒤 노드로 변경
			}else{ //다음 노드로 이동해 삭제할 노드를 찾음
					n = n.next;
			}
		}
	}

	//현재 어떤 노드들이 있는지 확인해주는 함수.
	void retrieve(){
		Node n = header.next;
		while(n.next != null){ //마지막 노드 전까지
			System.out.print(n.data+"->");
			n = n.next; //다음노드로 이동
		}
		System.out.println(n.data); //맨마지막 노드는 가지않으니
	}


    //중복값 삭제
    void removeDups(){
        Node n = header;
        while(n != null && n.next != null){ //첫번째 노드에서 마지막까지
            Node r = n; //n의 위치에서 시작
            while (r.next != null) {
                if(n.data == r.next.data){ //중복되는지
                    r.next = r.next.next; //다음데이터로 바꿈.
                }else{
                    r = r.next;
                }
            }
            n = n.next; //r이 한바뀌 돌고오면 다음 n으로 가서 비교!
        }
    }

    public Node get(int i) {
        Node n = header;
        for(int j=0;j<i;j++){
            n = n.next;
        }
        return n;
    }


 
}

public class RemoveDups {
    public static void main (String[] args){
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.retrieve();

        deleteNode(ll.get(2));
        ll.retrieve();

    
    }

    //중간값 삭제
    private static boolean deleteNode(LinkedList.Node n) {
        if(n == null || n.next == null){ //but 처음과 마지막은 지울 수 없는 로직
                return false;
            }
        LinkedList.Node next = n.next;
        n.data = next.data;
        n.next = next.next;

        return true;
    }

    
    
}


