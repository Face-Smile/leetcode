import java.util.*;
/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum-closest
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/


class Solution {
    public static void main(String args[]){
        Solution s = new Solution();
        // int nums[] = {1,1,1};
        int nums[] = {-1,2,1,-4};
        int target = 1;
        System.out.println(s.threeSumClosest(nums, target));

    }


    // public int threeSunCloset(int[] nums, int target, int ways){
    //     Arrays.sort(nums);
    //     int res = 
    // }

    public int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2;){
            int start = i + 1;
            int end = nums.length - 1;
            while(start < end){
                int value = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - res) > Math.abs(target - value))
                    res = value;
                if(value < target)
                    start++;
                else if(value > target){
                    end--;
                }else{
                    return value;
                }
            }
            while(i < nums.length - 2 && nums[i] == nums[++i]){}
        }
        return res;
    }


    /* 方法一：穷举法 */
    // public int threeSumClosest(int[] nums, int target) {
    //     int minspan = Math.abs(nums[0] + nums[1] + nums[2] - target);
    //     int minvalue = nums[0] + nums[1] + nums[2];
    //     for(int i = 0; i < nums.length - 2; i++){
    //         for(int j = i + 1; j < nums.length - 1; j ++){
    //             for(int k = j + 1; k < nums.length; k ++){
    //                 int span = Math.abs(nums[i] + nums[j] + nums[k] - target);
    //                 if(span < minspan){
    //                     minspan = span;
    //                     minvalue = nums[i] + nums[j] + nums[k];
    //                 }
    //                 if(minspan == 0)
    //                     return minvalue;
    //             }
    //         }
    //     }
    //     return minvalue;
    // }



}