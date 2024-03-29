/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *  思路：
 *      倒序输出
 */
public class Reverse {
    /*public int reverse(int x) {
        int flag = 1;
        if(x < 0)
            flag = -1;
        String s = Integer.toString(Math.abs(x));
        char[] c = new char[s.length()];
        for(int i = 0 ; i < s.length(); i++)
            c[s.length() - i - 1] = s.charAt(i);
        try {
            return Integer.parseInt(new String(c)) * flag;
        }catch (Exception e){
            return 0;
        }
    }*/

    public int reverse(int x) {
        int flag = x < 0 ? -1 : 1;
        long result = 0;
        x = Math.abs(x);

        while(true){
            result += (x % 10);
            x /= 10;
            if(x == 0 )
                break;
            else{
                result *= 10;
            }
        }
        result *= flag;
        if(result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE)
            return (int)result;
        else
            return 0;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Reverse().reverse(120));
//        System.out.println(4294967295);
    }
}
