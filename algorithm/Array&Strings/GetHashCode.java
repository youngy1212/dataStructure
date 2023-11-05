import java.util.LinkedList;

class HashTable{
    class Node{
        String key;
        String value;
        Node(String key, String value){
            this.key = key;
            this.value = value;
        }
        String value(){
            return value;
        }
        void value(String value){
            this.value = value;
        }
    }
    
    LinkedList<Node>[] data;
    //크기를 미리 정함
    HashTable(int size){
        this.data = new LinkedList[size];
    }

    //해쉬 코드를 반환
    int getHashcode(String key){
        int hashcode = 0;
        for(char c : key.toCharArray()){
            hashcode += c;
        }
        return hashcode;
    }

    //해쉬코드를 배열방 인덱스로 변환
    int convertToIndex(int hashcode){
        return hashcode % data.length;
    }
    Node searchKey(LinkedList<Node> list, String key){
        if(list == null) return null;
        for(Node node :list){
            if(node.key.equals(key)){
                return node;
            }
        }
        return null;
    }
    //데이터 받아서 저장하는 함수
    void put(String key, String value){
        int hashcode = getHashcode(key);
        int index = convertToIndex(hashcode);
        LinkedList<Node> list = data[index]; //기존배열방 데이터 가져오기
        if(list ==null){ //null이면 linkedlist 생성
            list = new LinkedList<Node>();
            data[index] = list; //해당 리스트를 배열방에 넣어줌
        }
        Node node = searchKey(list, key); //해당키로 데이터를 가지고 있는지
        if(node ==null){ //데이터가 없음
            list.addLast(new Node(key, value));
        }else{
            node.value(value); //내용만 변경
        }
    }

    //가져오는 함수
    String get(String key){
        int hashcode = getHashcode(key);
        int index = convertToIndex(hashcode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null? "Not foound" : node.value();
    }

}



public class GetHashCode {

    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("a", "aaa");
        h.put("b", "bbb");
        h.put("c", "ccc");
        h.put("d", "ddd");
        h.put("e", "eee");
        System.out.println(h.get("a"));
        System.out.println(h.get("b"));
            
    }
}