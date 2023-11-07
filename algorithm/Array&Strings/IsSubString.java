
public class IsSubString {
    public static void main(String[] args) {
        System.out.println(isRotation("String", "ringSt"));
        System.out.println(isRotation("String", "ringSr"));
    }

    private static boolean isRotation(String s1, String s2){ //회전체크
        if(s1.length() != s2.length()) return false;
        return isSubString(s1+s1, s2);
    }
    private static boolean isSubString(String s1, String s2){ //포함체크
        return s1.contains(s2);
    }
    
}