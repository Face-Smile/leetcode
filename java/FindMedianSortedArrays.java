import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 思路：
 * 1.计算数组的长度，算出中位数的位置（分奇数长度，偶数长度）
 * 2.遍历数组，进行排序找到指定位置的数
 * 3.计算中位数的值
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        //int nums1[] = {}; //{1, 2, 5};
        //int nums2[] = {3, 4};
        int nums1[] = {2, 3}; //{1, 2, 5};
        int nums2[] = {1};
        System.out.println(new FindMedianSortedArrays().findMedianStoreArrays(nums1, nums2));
    }

    public double findMedianStoreArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            int[] nums;
            if (nums1 == null || nums1.length == 0) {
                nums = nums2;
            } else {
                nums = nums1;
            }
            if (nums.length % 2 == 1)
                return nums[length / 2];
            else
                return (nums[length / 2] + nums[length / 2 - 1]) / 2.0;
        }


        int len = length / 2;
        int i = 0, j = 0;
        int[] array = new int[2];

        while (i < nums1.length && j < nums2.length && len >= 0) {
            System.out.println(Arrays.toString(array));
            if (nums1[i] < nums2[j]) {
                array[len-- % 2] = nums1[i++];
            } else {
                array[len-- % 2] = nums2[j++];
            }
        }

        if (i >= nums1.length) {
            while (j < nums2.length && len >= 0) {
                System.out.println("nums1: " + Arrays.toString(array));
                array[len % 2] = nums2[j++];
                len--;
            }
        } else if (j >= nums2.length) {
            while (i < nums1.length && len >= 0) {
                System.out.println("nums2: " + Arrays.toString(array));
                array[len % 2] = nums1[i++];
                len--;
            }
        }

        if (length % 2 == 1)
            return Math.max(array[0], array[1]);
        else
            return (array[0] + array[1]) / 2.0;

    }


    /*public double findMedianStoreArray_Odd(int[] nums1, int[] nums2, int len){
        double median = 0f;
        int i = 0, j = 0;
        while(len > 0){
            if(nums1[i] < nums1[j]){
                j ++;

            }else{
                i++;
            }
            len--;
        }
        return median;
    }


    public double findMedianStoreArray_Even(int[] nums1, int[] nums2, int len){
        int length = nums1.length + nums2.length / 2;
        double median = 0f;
        int temp_len = length / 2 + length % 2;
        while(temp_len > 0){

        }

        return median;
    }*/
}
