/**
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zigzag-conversion
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *      本质为找规律：
 *      可以把Z型排列看成多个如下图形的组成
 *          *
 *          * *
 *          *
 *      规律如下：
 *          假设： n为行数，c为每一行字符串的第几个
 *          则：
 *              第一行字符的索引：1 + (2n - 2) * (c - 1)
 *              第二行字符的索引：2 + (2n - 2) * (c - 1),  (2n - 2) * c
 *              第三行字符的索引：3 + (2n - 2) * (c - 1),  (2n - 2) * c - 1
 *
 */
class Convert {
    public String convert(String s, int numRows) {
        char[] chars = new char[s.length()];
        int index = 0;
        int span = 2 * numRows - 2;
        for(int i = 1; i <= numRows; i ++){
            int pan = span - (i - 1) * 2;
            if(pan <= 0) pan = span;
            int per_index = i - 1;
            while(per_index < s.length()){
                System.out.println(new String(chars) + ", per_index: " + per_index + ", index: " + index + ", pan: " + pan);

                chars[index++] = s.charAt(per_index);
                if(pan < span && per_index + pan < s.length()){
                    chars[index++] = s.charAt(per_index + pan);
                }
                per_index += span;
            }
        }
        return new String(chars);
    }

    public static void main(String args[]){
        String s = "LEETCODEISHIRING";
        System.out.println(s.length());
        System.out.println(new Convert().convert(s, 4));
//        System.out.println("LCIRETOESIIGEDHN");
        System.out.println("LDREOEIIECIHNTSG");
    }
}
