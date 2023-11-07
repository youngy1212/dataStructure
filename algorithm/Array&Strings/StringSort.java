
public class StringSort {

    private static final int C =26; //알파벳수 상수 선언

    public static void main(String[] args) {
        printSortedString(2);
    }

    private static void printSortedString(int k){
        printSortedString(k, "");
    }

    private static void printSortedString(int k, String prefix) {
        if(k == 0){ 
            if(isInOrder(prefix)){ //정렬 확인
                System.out.println(prefix);
            }
        }else{
            for(int i = 0; i<C; i++ ){ //알파벳 한자씩 가져옴
            char c = ithLetter(i);
            printSortedString( k-1, prefix+c);
            }
        } 
    }

    //정렬되었는지 확인
    private static boolean isInOrder(String s) {
        for(int i = 1; i<s.length(); i++){
            int prev = ithLetter(s.charAt(i -1 ));
            int curr = ithLetter(s.charAt(i));
            if(prev > curr){
                return false;
            }
        }
        return true;
    }

    //아스키값 반환
    private static char ithLetter(int i) {
        return (char) (((int) 'a') + i);
    }
    
    
}