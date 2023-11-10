import java.util.*;

public class Implementation3 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String inputData = sc.nextLine();

        int row = inputData.charAt(1) - '0'; //아스키 코드값을 int로 변경
        int column = inputData.charAt(0) - 'a'+1; 

        // 나이트가 이동할 수 있는 8가지 방향 정의
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        // 8가지 방향에 대하여 위치로 이동할 수 있는지 체크
        int result = 0;
        for(int i = 0; i < 8; i++){

            //이동 하려고 하는 위치
            int nextRow = row + dx[i];
            int nextColumn = column + dy[i];

            if (nextRow >= 1 && nextRow <= 8 && nextColumn >= 1 && nextColumn <= 8) {
                result += 1;
            }
            
        }

        System.out.println(result);

    }
}
