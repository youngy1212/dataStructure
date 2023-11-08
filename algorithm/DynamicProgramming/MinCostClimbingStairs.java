
public class MinCostClimbingStairs {

    public static void main(String[] args) {


        int[] cost = new int[] {1,2,3,4,5,6,7};
        int result = minCostClimbingStairs(cost);
        System.out.println(result);
        
    }

    public static int minCostClimbingStairs(int[] cost){
        int case1 = 0, case2 = 0, current; //매번 경우의 수가 2개 나옴(최소비용), 현재계단 임시비용
        for(int i = cost.length -1; i>=0; --i){ //뒤에서부터 앞으로
            current = cost[i] + Math.min(case1, case2); //현재비용의 최소값을 계산
            case2 = case1; //한칸은 두칸멀리
            case1 = current; //현재는 한칸멀리
        }
        return Math.min(case1, case2);
    }
}