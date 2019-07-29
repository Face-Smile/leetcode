import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/


class Solution_4 {
    public static void main(String args[]){
        Solution_4 s = new Solution_4();
        // int nums[] = {};
        // int nums[] = {-1, 0, 1, 2, -1, -3, -1};
        int nums[] = {0, 0, 0};
        List<List<Integer>> list = s.threeSum(nums);
        for(List l: list){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }

    public List<int[]> twoSum(int[] nums, int target, int start){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<int[]> list = new ArrayList<int[]>();
        for(int i = start; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                list.add(new int[] {map.get(complement), i});
            }
            map.put(nums[i], i);
        }
        return list;
    } 

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        // HashSet<Integer> h = new HashMap<Integer>();
        // List<Integer> numsList = new ArrayList<Integer>();
        // for(int num: nums){
        //     h.add(num);
        // }
        Arrays.sort(nums);
        if(nums.length != 0 && nums[0] <= 0 && nums[nums.length - 1] >= 0){
            for(int i = 0; i < nums.length - 2; ){
                // System.out.println(Arrays.toString(nums));

                if(nums[i] > 0){    // 最左边大于0，不可能找到满足条件的值
                    break;
                }
                int first = i + 1;
                int last = nums.length - 1;
                while(first < last && nums[i] <= 0 && nums[last] >= 0){ // 使用双指针，判断是否满足三者符号不全相同
                    // if(first >= last || nums[i] * nums[last] > 0) break; 
                    int res = nums[i] + nums[first] + nums[last];
                    if(res == 0){   // 符合要求，记录下来
                        List<Integer> r = new ArrayList<Integer>();
                        r.add(nums[i]);
                        r.add(nums[first]);
                        r.add(nums[last]);
                        list.add(r);
                    }
                    if(res <= 0){  // 整体太弱，最左边往右移
                        while(first < last && nums[first] == nums[++first]){}
                    }else{  // 整体太强，最右边往左移
                        while(first < last && nums[last] == nums[--last]){}
                    }
                    // System.out.println(first + ", " + last);
                }
                while(i < nums.length - 2 && nums[i] == nums[++i]){}   // 过滤C位重复
            }
        }
        
        // for(int i=0; i < nums.length; i++){
        //     List<int[]> twoSumList = twoSum(nums, -nums[i], i+1);
        //     for(int[] arr: twoSumList){
        //         List<Integer> res = new ArrayList<Integer>();
        //         res.add(nums[i]);
        //         res.add(nums[arr[0]]);
        //         res.add(nums[arr[1]]);
        //         list.add(res);
        //     }
        // }

        // for(int i = 0; i < nums.length; i++){
        //     for(int j = i + 1; j < nums.length; j++){
        //         int index = numsList.indexOf(-(nums[i] + nums[j]));
        //         if(index > 0 && nums[index] == nums[index-1]){
        //             continue;
        //         }
        //         if(index > -1 && index < i){
        //             List<Integer> res = new ArrayList<Integer>();
        //             res.add(nums[index]);
        //             res.add(nums[i]);
        //             res.add(nums[j]);
        //             list.add(res);
        //         }
        //     }
        // }
        return list;
    }
}