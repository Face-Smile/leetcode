import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]. 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<String>();
        if(digits == null || digits.isEmpty()) return ans;
        char[] keys = digits.toCharArray();
        String[] values = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        
        List<StringBuffer> list = letterCombinations(keys, values, 0, digits.length() - 1);
        for (StringBuffer var : list) {
            ans.add(var.toString());
        }
        return ans;
    }

    public List<StringBuffer> letterCombinations(char[] keys, String[] values, int i, int j) {
        List<StringBuffer> list = new ArrayList<StringBuffer>();
        String value = values[keys[i] - 50];
        for (int k = 0; k < value.length(); k++) {
            if (i + 1 <= j) {
                List<StringBuffer> temp = letterCombinations(keys, values, i + 1, j);
                for (StringBuffer var : temp) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(value.charAt(k));
                    sb.append(var);
                    list.add(sb);
                }
            }else{
                StringBuffer sb = new StringBuffer();
                sb.append(value.charAt(k));
                list.add(sb);
            }

        }

        return list;
    }

    //回溯法
    private List<String> res;
    private String[] values = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations_1(String digits){
        res = new ArrayList<String>();
        traceback(new StringBuilder(""), digits);
        return res;
    }
    public void traceback(StringBuilder sb, String digits){
        if(digits.length() == 0){
            res.add(sb.toString());
        }
        else{
            // System.out.println("digits:" + digits);
            // System.out.println("index: " + (digits.charAt(0) - 50));
            String value = values[digits.charAt(0) - 50];
            for(int i = 0; i < value.length(); i++){
                traceback(sb.append(value.charAt(i)), digits.substring(1));
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "";
        // System.out.println(sb.append(new StringBuffer("123")));
        System.out.println((new LetterCombinations().letterCombinations_1(s)));
    }
}