public class StringPermutation {
    public static void main(String[] args) {
        System.out.println(permutation2("ABCA", "BCAA"));
        System.out.println(permutation2("ABCA", "BCRA"));
    }
    //정렬함수
    private static String sort(String s){
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content); //정렬
        return new String(content); 
    }
    //두개의 문자열이 치환인지 체크
    private static boolean permutation(String s, String t){
        if(s.length() != t.length()) return false;
        //길이가 다르면 치환X
        return sort(s).equals(sort(t));
    }

    private static boolean permutation2(String s, String t){
        if(s.length() != t.length()) return false;
        int[] letters = new int[128];
        for(int i =0; i<s.length(); i++){
            letters[s.charAt(i)]++; //값이 있으면 증가
        }

        for(int i = 0; i<t.length(); i++){
            letters[t.charAt(i)]--;
            if(letters[t.charAt(i)] < 0 ) return false; 
        }
        
        return true;
    }
}
