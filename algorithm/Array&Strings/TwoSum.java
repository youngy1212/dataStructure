import java.util.HashMap;


public class TwoSum {
    public static void main(String[] args) throws IllegalAccessException {
        int[] nums = {6,4,3,8,7,5,2};
        int[] result = twosum3(nums,5);
        System.out.println(result[0]+" "+ result[1]);


    }

    //방법1. 포인터
    public static int[] twosum(int[] nums, int target) throws IllegalAccessException{
        for(int i =0; i<nums.length; i++){
            for(int j = i+1; j<nums.length; j++){
                if(target == nums[i] + nums[j]){
                    return new int[] {i,j};
                }
            }
        }
        throw new IllegalAccessException("No two sum solution");
    }
    
    //방법2. HashMap을 사용
    public static int[] twosum2(int[] nums, int target) throws IllegalAccessException{
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i =0; i<nums.length; i++){
            map.put(nums[i], i);//map에 복사
        }
        for(int i1 = 0; i1<nums.length;i1++){ //배열을 다시 돌면서 target 값이 나오는 hash가 있는지 검색
            Integer i2 = map.get(target-nums[i1]);
            if(i2 != null && i1 != i2) return new int[] {i1,i2}; //자기자신 제외하고 값이 있다면 
        }
        throw new IllegalAccessException("No two sum solution");
    }

    //방법3. HashMap과 배열을 동시에 사용
    public static int[] twosum3(int[] nums, int target) throws IllegalAccessException{
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i<nums.length;i++){ 
           if(map.containsKey(target -nums[i])){
            return new int[]{map.get(target -nums[i]), i};
           }

           //못찾은겨 경우
           map.put(nums[i], i);

        }
        throw new IllegalAccessException("No two sum solution");
    }



}
