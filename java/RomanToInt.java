import java.util.Map;
import java.util.HashMap;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 
 * 字符 数值 I 1 V 5 X 10 L 50 C 100 D 500 M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12
 * 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * 
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40
 * 和 90。  C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 给定一个罗马数字，将其转换成整数。输入确保在
 * 1 到 3999 的范围内。
 * 
 * 示例 1:
 * 
 * 输入: "III" 输出: 3 示例 2:
 * 
 * 输入: "IV" 输出: 4 示例 3:
 * 
 * 输入: "IX" 输出: 9 示例 4:
 * 
 * 输入: "LVIII" 输出: 58 解释: L = 50, V= 5, III = 3. 示例 5:
 * 
 * 输入: "MCMXCIV" 输出: 1994 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * 思路：
 *      规律：发现组合的罗马数字：如果大的在前面，则这个组合的值为各个部分的值的和，如果小的值在前面，则为最大的减去小的的余下的值
 */

class RomanToInt {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        // int[] key = {1000, 500, 100, 50, 10, 5, 1};
        // char[] value= {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int ans = 0;
        for (int i = s.length() - 1, j = s.length(); i >= 0; i--) {
            if (j < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(j))) {
                ans -= map.get(s.charAt(i));
            } else {
                ans += map.get(s.charAt(i));
                j = i;
            }
        }
        return ans;
    }

    public int romanToInt_1(String s) {
        // int[] key = {1000, 500, 100, 50, 10, 5, 1};
        // char[] value= {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int ans = 0;
        for (int i = s.length() - 1, j = i; i >= 0; i--) {
            int a = getValue(s.charAt(i));
            int b = getValue(s.charAt(j));
            if (a < b) {
                ans -= getValue(s.charAt(i));
            } else {
                ans += a;
                j = i;
            }
        }
        return ans;
    }

    public int getValue(char key) {
        switch (key) {
        case 'M':
            return 1000;
        case 'D':
            return 500;
        case 'C':
            return 100;
        case 'L':
            return 50;
        case 'X':
            return 10;
        case 'V':
            return 5;
        case 'I':
            return 1;
        default:
            return 0;
        }
    }

    public static void main(String[] args) {
        String values[] = { "III", "IV", "IX", "LVIII", "MCMXCIV" };
        for (String var : values) {
            System.out.println(new RomanToInt().romanToInt_1(var));
        }
    }
}