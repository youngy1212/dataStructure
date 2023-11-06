public class StringUrl {
    public static void main(String[] args) {
        System.out.println(URLify("Mt John Smith    ",13));
    }
    private static String URLify(String str, int len){
        return URLify(str.toCharArray(), len);
    }
    private static String URLify(char[] str, int len) {
        int spaces = 0; //공백갯수 
        for(int i =0; i<len; i++){
            if(str[i] == ' ') spaces++;
        }
        //길이 계산
        int index = len + spaces*2 - 1;
        for(int p = len -1; p>=0; p--){ //뒤에서 부터 진행
            if(str[p] == ' '){
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            }else{
                str[index--] = str[p];
            }
        }

        return new String(str);
    }
    
}
