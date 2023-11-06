public class EditedOnlyOnce {
    public static void main(String[] args) {
        System.out.println(isOneAway("pal", "pale"));
        System.out.println(isOneAway("pale", "pal"));
        System.out.println(isOneAway("pale", "bale"));

        System.out.println(isOneAway("pal", "pales"));
        System.out.println(isOneAway("pale", "pel"));
        System.out.println(isOneAway("pale", "bake"));

    }

    private static boolean isOneAway(String s1, String s2){
        String ss, ls; 
        if(s1.length() < s2.length()){
            ss = s1; //긴문자열
            ls = s2; //짧은 문자열
        }else{
            ss = s2; 
            ls = s1;
        }

        //ss에서 한글자 추가된게 ls인지 확인하면됨
        if(ls.length() - ss.length() > 1) return false;
        boolean flag = false; //다른문자가 나왔는지 할 falg
        for(int i = 0, j = 0; i<ss.length(); i++,j++  ){
            if(ss.charAt(i) != ls.charAt(i)){ 
                if(flag){
                    return false;
                }
                flag = true;
                
            }
            if(ss.length() != ls.length()){//문자열 길이가 다른경우
                    //삽입 삭제
                    flag=true; //긴것만 한칸더
            }
        }
        return true;
    }
}
