import java.util.HashMap;
import java.util.Map;


/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution_1 {
    //字符串可以包含非ASCII字符
    /*public int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        Map<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int length = 0;
        for (int i = 0, j = 0; j < s.length() && s.length() - i > length; j++) {
            if (hashMap.containsKey(s.charAt(j))) {
                i = Math.max(hashMap.get(s.charAt(j)), i);
            }
            length = Math.max(length, j - i + 1);
            hashMap.put(s.charAt(j), j + 1);
        }
        return length;
    }*/


    //如果字符串仅包含ascii字符
    public int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        int length = 0;
        int[] record = new int[256];
        for (int i = 0; i < record.length; i++) {
            record[i] = -1;
        }
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (record[s.charAt(j)] != -1) {
                i = Math.max(record[s.charAt(j)], i);
            }
            length = Math.max(length, j - i + 1);
            record[s.charAt(j)] = j + 1;
        }

        return length;

    }

    public static void main(String[] args) {
        System.out.println(new Solution_1().lengthOfLongestSubstring("pwwkew"));
    }
}


