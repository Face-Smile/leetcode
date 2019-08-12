import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"] 输出: "fl" 示例 2:
 * 
 * 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class LongestCommonPrefix {

    // 普通思维：遍历比较
    public String longestCommonPrefix(String[] strs) {
        StringBuffer buf = new StringBuffer();
        int index = 0, min_len = 0;
        min_len = strs.length > 0 ? strs[0].length() : 0;
        for(int i = 1; i < strs.length; i++){
            min_len = Math.min(min_len, strs[i].length());
        }
        while(index < min_len){
            char c = strs[0].charAt(index);
            for(int i = 1; i < strs.length; i++){
                if(strs[i].charAt(index) != c) return buf.toString();
            }
            buf.append(c);
            index ++;
        }
        return buf.toString();
    }

    // 水平扫描法
    public String longestCommonPrefix_shuipinsaomiao(String[] strs){
        if(strs == null || strs.length == 0) return "";
        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                // System.out.println("i: " + i);
                // System.out.println("j: " + j);
                if(i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    // 水平扫描
    public String longestCommonPrefix_shuipinsaomiao_2(String[] strs){
        if(strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for(int i= 1; i < strs.length; i++){
            // 如果找不到重复的字符串，就减少字符串的长度
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // 分治法
    public String longestCommonPrefix_fenzhifa(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        return fenzhi(strs,0, strs.length-1);
    }
    public String fenzhi(String strs[], int i, int j){
        if(i == j) return strs[i];
        int mid = (i + j) / 2;
        String leftSide = fenzhi(strs, i, mid);
        String rightSide = fenzhi(strs, mid + 1, j);
        return compareTwo(leftSide, rightSide);
    }
    public String compareTwo(String left, String right){
        int min = Math.min(left.length(), right.length());
        for(int i = 0; i < min; i++){
            if(left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }



    // 二分法
    /**
     * 找出最小的字符串长度，对它分两部分比较
     * 如果前半部分匹配，则前半部分不用在比较，对后半部分继续比较；
     * 如果前半部分不匹配，则后半部分不用匹配，继续对前半部分二分比较
     * @param strs
     * @return
     */    
    public String longestCommonPrefix_erfenfa(String strs[]){
        if(strs == null || strs.length == 0) return "";
        int min_len = strs[0].length();
        for(int i = 1; i < strs.length; i++){
            min_len = Math.min(min_len, strs[i].length());
        }
        return erfenfa(strs, 0, min_len);   
    }
    
    // 编写出现的问题：
    /**
     * 当j-i == 1时出现无限循环:
     *      解决：当j-i==0 时候前半部分为(i,i)，后半部分为（i,j)
     *          单独处理后半部分j-i == 1的情况
     */
    public String erfenfa(String strs[], int i, int j){
        System.out.println("i: " + i + ", j: " + j);
        if(i==j) return "";
        int mid = (i + j) / 2;
        String left = strs[0].substring(i, mid);
        if(!left.isEmpty())
            for(int k = 1; k < strs.length; k++)
                if(strs[k].indexOf(left) != 0)
                    return erfenfa(strs, 0, mid);
        String right = strs[0].substring(mid, j);
        for(int k = 1; k < strs.length; k++)
            if(strs[k].indexOf(right, mid) != mid)
                if(j-i == 1)
                    return strs[0].substring(0, mid);
                else
                    return erfenfa(strs, mid, j);
        
        return strs[0].substring(0, j);
    }


    //二分法——leetcode
    public String longestCommonPrefix_erfenfa_leetcode(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }
    
    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }


    // 字典树
    public String longestCommonPrefix_trie(String[] strs){
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        TireNode links = new TireNode();
        int min_len = Integer.MAX_VALUE;
        for (String str : strs) {
            min_len = Math.min(min_len, str.length());
        }
        TireNode node ;
        for(int i = 1; i < strs.length; i++){
            node = links;
            for(int j = 0; j < min_len; j++){
                TireNode next = node.getKey(strs[i].charAt(j));
                if(next == null){
                    node.put(strs[i].charAt(j), new TireNode());
                    node = node.getKey(strs[i].charAt(j));
                }else{
                    node = next;
                }
            }
            node.setEnd(true);
            
        }
        
        // 遍历字典树查找最长前缀
        return searchTire(strs[0], links);
    }

    public String searchTire(String str, TireNode node){
        int len = 0;
        for(int i = 0; i < str.length(); i ++){
            if(node.getLinks() == 1 && node.getKey(str.charAt(i)) != null && !node.getEnd())
                len++;
            else
                break
            node = node.getKey(str.charAt(i));
        }
        return str.substring(0, len);
    }

    public static void main(String[] args) {
        System.out.println("start....");
        String[][] ques = {{"aaa", "aaaa"}, {"f", "flower","flow","flight"}, {"dog","racecar","car"}};
        for(String[] strs: ques){
            // System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
            // System.out.println(new LongestCommonPrefix().longestCommonPrefix_shuipinsaomiao(strs));
            // System.out.println(new LongestCommonPrefix().longestCommonPrefix_shuipinsaomiao_2(strs));
            // System.out.println(new LongestCommonPrefix().longestCommonPrefix_fenzhifa(strs));
            System.out.println(new LongestCommonPrefix().longestCommonPrefix_trie(strs));
            // break;

        }
        System.out.println("end.....");
    }
}


class TireNode{
    private TireNode[] links;
    private final int R = 26;
    private boolean isEnd;
    private int size;
    public TireNode(){
        this.links = new TireNode[this.R];
        this.isEnd = false;
        this.size = 0;
    }

    // 返回子节点的长度
    public int getLinks(){
        return this.size;
    }

    public void put(char ch, TireNode node){
        this.links[ch-'a'] = node;
        this.size ++;
    }

    public TireNode getKey(char ch){
        return this.links[ch - 'a'];
    }

    /**
     * @param isEnd the isEnd to set
     */
    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    public boolean getEnd(){ return this.isEnd;}
}

