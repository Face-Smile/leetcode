import java.util.Arrays;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 
 */
class IsMatch{
    public boolean isMatch(String s, String p) {
        
        
        // return match_normal(s, p, 0, 0);
        // return match_traceback(s, p);

        return match_dymaic_plan_bottom_up(s, p);
        
    }

    // 回溯法
    public boolean match_normal(String s, String p, int i, int j){
        // 如果匹配模式串为空，则判断匹配串是否为空
        if(j==p.length()) return i==s.length();
        // 判断匹配串是否为空并且首字符是否匹配
        boolean first_match = (i != s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // 判断匹配模式串是否含有适配符'*'
        // 如果有则判断0个匹配或（1个匹配&&余下字符是否匹配）
        // 如果没有则判断是否（首个匹配&&余下字符串匹配）
        if(p.length()-j>=2 && p.charAt(j+1) == '*'){
            return (match_normal(s, p, i, j+2) || (first_match && match_normal(s, p, i+1, j)));
        }else{
            return first_match && match_normal(s, p, i+1, j+1);
        }
    }
    // 同上
    public boolean match_traceback(String text, String pattern){
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }


    // 动态规划
    // 方法一 自底向上
    public boolean match_dymaic_plan_bottom_up(String s, String p){
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;

        for(int i = s.length()-1; i >= 0; i--){
            for(int j = p.length()-1; j >= 0; j--){
                if(p.charAt(j) == '*'){
                    dp[i][j] = dp[i+1][j+1] && ((j>=2 && s.charAt(i) == p.charAt(j-2)) || s.charAt(i) == p.charAt(j-1));
                }else if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
                    dp[i][j] = true && dp[i+1][j+1];
                else{
                    dp[i][j] = false;
                    // 子串一旦不匹配，前面都没必要在匹配，一定为false。
                    // break;
                }
                    
            }
        }
        return dp[0][0];
    }

    // 方法一 自底向上（leetcode 官方解答）
    public boolean isMatch_bottom_top(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                // 判断是否存在*通配符
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    // 判断当前字符和子串是否匹配
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        for(boolean[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        
        return dp[0][0];
    }

    // 方法二 自顶向下（leetcode 官方解答）
    Result[][] memo;
    public boolean match_dymaic_plan_top_down(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                       first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }



    

    public static void main(String[] args) {
        String s = "aaab";
        String p = "a*b";
        // System.out.println(new IsMatch().isMatch(s, p));
        System.out.println(new IsMatch().isMatch_bottom_top(s, p));
        
    }
}

enum Result {
    TRUE, FALSE
}
