

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 
 * 字符 数值 I 1 V 5 X 10 L 50 C 100 D 500 M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12
 * 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * 
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40
 * 和 90。  C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 给定一个整数，将其转为罗马数字。输入确保在 1 到
 * 3999 的范围内。
 * 
 * 示例 1:
 * 
 * 输入: 3 输出: "III" 示例 2:
 * 
 * 输入: 4 输出: "IV" 示例 3:
 * 
 * 输入: 9 输出: "IX" 示例 4:
 * 
 * 输入: 58 输出: "LVIII" 解释: L = 50, V = 5, III = 3. 示例 5:
 * 
 * 输入: 1994 输出: "MCMXCIV" 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * 思路：
 *      贪心算法：依次找到满足的最大值的罗马字符，然后减去找到的最大值，再用得到的值重复此步骤直到数值为0
 *      
 */

class IntToRoman {
    public String intToRoman(int num) {
        // char[] romans = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        StringBuffer res = new StringBuffer();
        while (num != 0) {
            if (num >= 1000) {
                res.append('M');
                num -= 1000;
            } else if (num >= 900) {
                res.append("CM");
                num -= 900;
            } else if (num >= 500) {
                res.append('D');
                num -= 500;
            } else if (num >= 400) {
                res.append("CD");
                num -= 400;
            } else if (num >= 100) {
                res.append('C');
                num -= 100;
            } else if (num >= 90) {
                res.append("XC");
                num -= 90;
            } else if (num >= 50) {
                res.append("L");
                num -= 50;
            } else if (num >= 40) {
                res.append("XL");
                num -= 40;
            } else if (num >= 10) {
                res.append('X');
                num -= 10;
            } else if (num >= 9) {
                res.append("IX");
                num -= 9;
            } else if (num >= 5) {
                res.append('V');
                num -= 5;
            } else if (num >= 4) {
                res.append("IV");
                num -= 4;
            } else if (num >= 1) {
                res.append('I');
                num -= 1;
            }

        }
        return res.toString();
    }

    public String intToRoman_1(int num) {
        int arr[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String values[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int i = 0;
        StringBuffer ans = new StringBuffer();
        while (num > 0) {

            if (num >= arr[i]) {
                ans.append(values[i]);
                num -= arr[i];
                i = 0;
            } else
                i++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int arr[] = { 3, 4, 9, 58, 1994 };
        for (int var : arr) {
            System.out.println(new IntToRoman().intToRoman_1(var));
        }
    }

}