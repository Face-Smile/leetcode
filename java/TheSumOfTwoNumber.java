import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;


class Solution_5 {
    public static void main(String args[]){
        Solution_5 s = new Solution_5();
        int[] nums = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(s.twoSum(nums, target)));
    }

    // public int[] twoSum(int[] nums, int target){
    //     for(int i=0; i < nums.length; i++){
    //         for(int j = i + 1; j < nums.length; j ++){
    //             if(nums[i] + nums[j] == target) {
    //                 return new int[] {i, j};
    //             }
    //         }

    //     }
    //     throw new IllegalArgumentException("No two num Solution_5.");
    // }
    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two num Solution_5.");
    }
}