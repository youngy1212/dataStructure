
class StringBuilder{
    private char[] value;
    private int size; //배열방 크기 
    private int index; //새로 추가될 배열방 인덱스

    StringBuilder(){
        size = 1;
        value = new char[size];
        index = 0; //새로 추가할 데이터는 0번 부터 저장
    }

    public void append(String str){
        if(str == null) str = "null";
        int len = str.length();
        ensureCapacity(len); //부족하면 늘려주기
        for(int i =0; i<str.length(); i++){
            value[index] = str.charAt(i);
            index++;
        }
    }

    //배열방의 크기를 늘려주는 함수
    private void ensureCapacity(int len){
        if(index + len > size){
            size = (size+len) *2;
            char[] newValue = new char[size];
            for(int i =0; i<value.length; i++){
                newValue[i] =value[i];
            }
            value = newValue;
        }
    }

    //모든 문자열을 붙여서 반환하는 함수
    public String toString(){
        return new String(value,0,index);
        //현재 저장된 배열방의 0번부터 index 문자열로 반환
    }

}


public class StringBuilderJava {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("sung");
        sb.append("Is");
        sb.append("pretty");
        System.out.println(sb.toString());
        
    }
    
}