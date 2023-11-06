public class StringPalindromePerMutation {
    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd ed"));
    }
    private static boolean isPermutationOfPalindrome(String s){
        int[] table = buildCharFrequencyTable(s); //문자를 세서 배열이 담고
        return checkMaxOneOdd(table); //해당배열이 홀수개의 문자 체크
    }
   
    private static int[] buildCharFrequencyTable(String s) {
        int[] table = new int[Character.getNumericValue('z') - Character.
            getNumericValue('a') +1]; //26개의 방을 만들어줌
        for(char c : s.toCharArray()){
            int x = getCharNumber(c); 
            if(x != -1){ //알파벳이 아닌경우
                table[x]++;
            }
        }
        return table;
    }

    //문자를 숫자로 변환
    private static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if(a <= val && val <= z ){ //a와 z 사이값임
            return val-a;
        }
        return -1; //알파벳이 아님
    }

    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false; //홀수개의 문자를 찾았는지
        for(int count : table){
            if(count % 2 == 1){
                if(!foundOdd){
                    foundOdd = true; //한개까지는 중앙값일 수 잆ㅇ므
                }else{
                    return false; //이미 1개 이상
                }
            }
        }
        return true;
    }

    //방법2 : 홀수개의 문자가 몇개인지 함께 세는것
    private static boolean isPermutationOfPalindrome2(String s){
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.
        getNumericValue('a') +1]; 
        for(char c : s.toCharArray()){
            int x = getCharNumber(c); 
            if(x != -1){ 
                table[x]++;
                if(table[x] % 2 == 1){ //홀수체크
                    countOdd++;
                }else{
                    countOdd--;
                }
            }
        }
        return countOdd <=1;

    }

    //방법3
    private static boolean isPermutationOfPalindrome3(String s){
       int bitVector = createBitVecotor(s); //비트값을 만드는 함수 호출
       return bitVector == 0 || checkExactlyOneBitSet(bitVector); //0 모두 짝수개 이거나 하나만 홀수개인지 체크하는 함수
    }
    
    //비트로 표현
    private static int createBitVecotor(String s) {
        int bitVector = 0; 
        for(char c : s.toCharArray()){
            int x = getCharNumber(c);
            bitVector = toggle(bitVector,x); 
        }
        return bitVector;
    }

    //배열방 1이면 0 으로 0이면 1로 toggle해주는 함수
    private static int toggle(int bitVector, int index) {
        if(index < 0) return bitVector; //원본백터 그래도 반환
        int mask = 1 << index; //배열방 번호만큼 이동해서 mask에 저장
        if((bitVector & mask) == 0){ //현재 문자위치가 0인지 1인지
            bitVector |= mask;  //짝수개라는 의미니 1을 더해줌 
        }else{ //홀수개
            bitVector &= ~mask; //1 -> 0으로 
        }
        return bitVector;
    }

    //하나만 홀수 개인지 확인하는 방법 
    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1) ) == 0;
    }

    
}
