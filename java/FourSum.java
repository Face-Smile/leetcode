import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * 思路： 最左边的值必定小于等于0，最右边的值必定大于等于0。
 * 
 * 
 */
class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return ans;
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        
        for (int i = 0; i < nums.length - 3; ) {
            for (int j = i + 1; j < nums.length - 2; ) {
                int a = target-(nums[i] + nums[j]);
                int m = j + 1, n = nums.length - 1;
                while (m < n) {
                    int b = nums[m] + nums[n];
                    if (b == a) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[m]);
                        list.add(nums[n]);
                        list.add(nums[j]);
                        ans.add(list);
                    }
                    if (b <= a) {
                        while (m < n && nums[m] == nums[++m]);
                    } else {
                        while (m < n && nums[n] == nums[--n]);
                    }
                }
                
                while (j < nums.length - 2 && nums[j] == nums[++j]);
            }
            while (i < nums.length - 3 && nums[i] == nums[++i]);
        }
        return ans;
    }

    public List<List<Integer>> fourSum_1(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return ans;
        Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
        for(int i =0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                int sum = nums[i] + nums[j];
                List<int[]> list = map.get(target-sum);
                if(list != null){
                    for (int[] var : list) {
                        if(i == var[0] || i == var[1] || j == var[0] || j == var[1])
                            continue;
                        List<Integer> res = new ArrayList<Integer>();
                        res.add(nums[var[0]]);
                        res.add(nums[var[1]]);
                        res.add(nums[i]);
                        res.add(nums[j]);
                        ans.add(res);
                    }
                }
                list = map.get(sum);
                int[] indexs = {i, j};
                if(list == null){
                    list = new ArrayList<int[]>();
                    
                    list.add(indexs);
                    map.put(sum, list);
                }else{
                    list.add(indexs);
                }
                
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 0, -1, 0, -2, 2,-3,4,-5,-6,5,7,8,9 };
        // int nums[] = {0,0,0,0};
        int target = 0;
        long count = 10000;
        long temp = count;
        System.out.println(new FourSum().fourSum(nums, target));
        System.out.println(new FourSum().fourSum_1(nums, target));
        long startTime = System.currentTimeMillis();
        while(temp-- > 0){
            new FourSum().fourSum_1(nums, target);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行" + count + "次耗时：" + (endTime - startTime)/* * 1.0 / count */  + "ms");
    }
}


