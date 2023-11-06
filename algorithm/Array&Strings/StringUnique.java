import java.util.HashMap;

public class StringUnique {
    public static void main(String[] args) {
        System.out.println(isUnique3("abdcd"));
        System.out.println(isUnique3("asdfghj"));
    }

    private static boolean isUnique(String str){
        if(str.length() > 128) return false; //ASCII 128자인데 128자 이상이면(최소 1개이상 중복)
        boolean[] char_set = new boolean[128]; //존재여부를 저장할 배열
        for(int i =0; i<str.length(); i++){
            int val = str.charAt(i);
            if(char_set[val]){
                return false; //중복!
            }
            char_set[val] = true;
        }
        return true;
    }

    private static boolean isUnique2(String str){
        HashMap<Integer, Boolean> hashmap = new HashMap<Integer, Boolean>();
        for(int i =0; i<str.length(); i++){
            int c = str.charAt(i);
            if(hashmap.containsKey(c)){
                return false;
            }
            hashmap.put(c, true);
        }
        return true;
    }

    
    private static boolean isUnique3(String str){
       int checker = 0; //각문자의 존재여부를 마킹 
       for(int i = 0; i<str.length(); i++){
        int val = str.charAt(i) - 'a'; //0부터 시작하기 위해
        if((checker & (1 << val)) > 0 ){ //1을 shift한 값이 비트연산자 안에 들었는지 체크
            return false;
        }

        checker |= (1<<val);  //chcker에 더해준다.ㄱ

       }
       return true;
    }
    
}