/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * <p>
 * 你的解法应该是 O(logN) 时间复杂度的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * <p>
 * 思路：
 *      分析：
 *          因为nums[i] != nums[i+1],所以在平面坐标中，是一条上升或或者下降的直线或者折线，不会出现平行的情况
 *          如果遍历的话，只要判断nums[i] 是否大于nums[i+1]即可得知其是否是峰值。但时间复杂度为O(N)
 *          可以采用二分搜索的方法：
 *              1.获取中间位置的值
 *              2.如果中间位置的值大于其右边的值，说明其右边处于下降或局部下降序列，得出必定有峰值在左边
 *              3.如果中间位置的值小于其右边的值，说明其右边处于上升或局部上升序列，得出必定有峰值在右边
 */
public class FindPeakElement {


    /**
     * for遍历，算法复杂度o(n)
     * @param nums
     * @return
     */
    /*public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }*/

    /**
     * 二分查找，时间复杂度：o(logn)
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {

            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 4, 1};
        System.out.println(new FindPeakElement().findPeakElement(nums));
    }
}
