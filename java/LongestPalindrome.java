import java.util.Arrays;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：
 *      动态规划：
 *          P(i, j) = true,     回文字符串
 *                    false,    其它情况
 *          P(i, j) = (p(i+1, j-1) = true && Si == Sj)
 */
public class LongestPalindrome {
    /*public String longestPalindrome(String s) {
        boolean[][] p = new boolean[s.length()][s.length()];
        int max_len = 0; // 最长回文字符串长度
        int l = 0; // 最长回文字符串起始坐标（从0开始）ß

        *//**
         * 以i为终点坐标，j为起点坐标遍历字符串，判断其是否为回文字符串
         * 长度小于等于2的字符串单独处理
         *//*
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if(i - j < 2){
                    p[j][i] = s.charAt(i) == s.charAt(j);
                }else{
                    p[j][i] = p[j+1][i-1] && s.charAt(i) == s.charAt(j);
                }
                if(p[j][i] && i - j + 1 > max_len){
                    max_len = i - j + 1;
                    l = j;
                }
            }

        }

        System.out.println("max_len: " + max_len + ", l: " + l);
        for (boolean[] q:p) {
            System.out.println(Arrays.toString(q));
        }
        return s.substring(l, l + max_len);

    }*/

    /**
     * 中心扩展算法
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int min_start=0,len=0;
        for(int i=0;i<s.length();){

            // 如果已找到的最大回文字符串的长度的一半大于当前坐标到串尾的距离则退出循环
            // 为什么是一半？
            // 因为扩张是以中心点向两端扩张的
            if(len/2>=s.length()-i)break;
            int j=i,k=i;

            // 找到从i开始第一个中心，即单一的zi'uu
            while(k<s.length()-1&&s.charAt(k+1)==s.charAt(k))k++;
            // 更改起始坐标到找到的第一个中心之后
            i=k+1;

            //扩展中心，直到最大
            while(j>0&&k<s.length()-1&&s.charAt(k+1)==s.charAt(j-1)){
                k++;
                j--;
            }
            //计算当前扩展中心的字符串长度是否大于以查找的最长回文字符串长度
            if(k-j+1>len){
                len = k-j+1;
                min_start = j;
            }
        }
        return s.substring(min_start,min_start+len);
    }

    /**
     * 中心扩展算法
     * @param s
     * @return
     */
    public String longestPalindrome_1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
         //保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
             //把回文看成中间的部分全是同一字符，左右部分相对称
             //找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }
    public int findLongest(char[] str, int low, int[] range) {
         //查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
         //定位中间部分的最后一个字符
        int ans = high;
         //从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
         //记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("baabaa"));
    }
}
