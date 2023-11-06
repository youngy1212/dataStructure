
public class StringCompression {
    public static void main(String[] args) {
        
    }

    
    private static String compressString(String str){
        String newStr = compress(str); 
        //두개의 길이를 비교 짧은 것을 반환
        return str.length() < newStr.length() ? str : newStr;
    }


    //문자를 압축할 함수
    private static String compress(String str) {
        int count; 
        StringBuilder newString = new StringBuilder(getTotal(str));
        for(int i = 0; i <str.length(); i++){
            count++;
            //맨 마지막 이거나, 해당문자가 다음 문자가 다른경우
            if(i +1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                newString.append(str.charAt(i));
                newString.append(count);
                count = 0; //0으로 초기화
            }
        }
        return newString.toString();
    }

    //반환갯수가 몇개일지 미리 받아옴
    private static int getTotal(String str) {
        int count = 0;
        int total = 0;
        for(int i =0; i<str.length(); i++){
            count++;
            if(i +1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                total += 1 + String.valueOf(count).length();
                count = 0;
            }
        }
        return total;
    }
}
