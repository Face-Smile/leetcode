/*
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/


class Solution_3 {
    public  static void main(String args[]){
        Solution_3 s = new Solution_3();
        System.out.println(s.multiply("3", "4210000000000000000000000"));
    }
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2))
            return "0";
        // if(num1.length() < num2.length()){
        //     String temp = num1;
        //     num1 = num2;
        //     num2 = temp;
        // }
        char[] charactres= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int[] num1Chars = new int[num1.length()];
        int[] num2Chars = new int[num2.length()];

        for(int i = 0; i < num1.length(); i++){
            num1Chars[num1.length() - 1 -i] = num1.charAt(i) - '0';
        }
        for(int i = 0; i < num2.length(); i++){
            num2Chars[num2.length()- 1 - i] = num2.charAt(i) - '0';
        }

        /*
            分析可知，n位*m位，其结果的位数在n+m-1~n+m范围内。
            假设结果为n+m-1位，然后从低位到高位计算每一位的值。
            通过分析得出以下规律(下标从0开始，即从第0位开始算)：
                第0位： 第0位*第0位(简写为：0 * 0， 下同)
                第1位： 1 * 0 + 0 * 1
                第2位： 2 * 0 + 1 * 1 + 0 * 2
                ......
                第n位： n*0 + (n-1) * 1 + ... + 0 * n
            注意到会出现下标越界的情况，所以要过滤所有的越界；
            发现越界都是两端才出现，都是因为第几位超出字符串数字的长度。
            所以控制好两端条件即可
        
            如果到最后还有进位，则表示其结果是n+m位，且多出的那一位的值就是进位的值；

        */
        int len = num1Chars.length + num2.length() - 1;
        int flag = 0;
        
        char[] res = new char[len];
        for(int i = 0; i < len; i++){
            int temp = flag;
            for(int j = (i < num1Chars.length ? i : num1Chars.length - 1); j >= 0 && (i-j) < num2Chars.length; j--){
                    System.out.println(j + ", " + (i-j));
                    temp += num1Chars[j] * num2Chars[i-j]; 
            }
            System.out.println("-----------");
            flag = temp / 10;
            res[len-1-i] = charactres[temp % 10]; 
        }
        StringBuffer str = new StringBuffer();
        if(flag == 0){
            str.append(res);
        }else{
            str.append(charactres[flag]).append(res);
        }
        return str.toString();
    }
}