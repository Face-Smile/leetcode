/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线
 * i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 
 * 
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 *  
 * 
 * 示例:
 * 
 * 输入: [1,8,6,2,5,4,8,3,7] 输出: 49
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MaxArea {
    public int maxArea(int[] height) {

        // return maxArea_qiongjiu(height);
        return maxArea_other(height);
    }

    // 穷举法
    public int maxArea_qiongjiu(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }

    // 其他方法
    public int maxArea_other(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            if (height[i] > height[j]) {
                j--;
            } else if (height[j] <= height[j]) {
                i++;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 77 };
        System.out.println(new MaxArea().maxArea(height));
    }
}

// 双指针法证明
/**
 * 双指针法正确性证明 以i,j表示前后指针，H[i]表示位置i处的高度，n是输入的数据长度。S(i,j) = min(H[i],H[j]) * (j -
 * i)S(i,j)=min(H[i],H[j])∗(j−i)是(i,j)对的面积。
 * 
 * 已知最大性 根据双指针法的求解过程，可以很容易得到一个性质：
 * 
 * H[i]与H[j]中至少有一个是在(0,i](0,i]和[j,n-1)[j,n−1)中，H最大的。
 * 
 * 反证一下，很容易证得上述性质。
 * 
 * 将(0, i)(0,i)与(j, n -1)(j,n−1)称为已知区域，[i,j][i,j]中间所有序偶对称未知区域。
 * 
 * 假设已知区域有H[k]为最大值，比H[i]和h[j]都大。那么很明显算法执行到k之后，将不可能获得移动机会，算法不可能移动到[i,
 * j]状态。因此假设不成立。
 * 
 * 安全性 深入思考，移动较小指针到底意味着什么？ 每移动一次较小指针，意味着计算了一对S(i, j)S(i,j)的值，而消去了一排S(i, k), i <
 * k <= jS(i,k),i<k<=j 我们定义移动指针的安全性： 如果一次移动指针，消去的所有(i,
 * j)(i,j)对，要么是被计算过，要么是可证明的小于某个已计算过的S(i, j)S(i,j)，那么我们可以说这一次指针移动是安全的。
 * 
 * 以初始状态为例，(i, j) = (0, n-1)(i,j)=(0,n−1)，那么该状态将计算S(0, n-1)S(0,n−1)。
 * 
 * 如果H[0] < H[n-1]，那么将消去(0, 1), (0,2) , ... ,
 * (0,n)(0,1),(0,2),...,(0,n)等n-1n−1个状态。
 * 
 * 而如果H[0] > H[n-1]，那么将消去(0, n-1), (1, n-1), ... ,(n - 2, n -
 * 1)(0,n−1),(1,n−1),...,(n−2,n−1)等n-1n−1个状态。
 * 
 * 定义消去集为XX ,待解集RR，R_i = R_{i-1} - XR i ​ =R i−1 ​ −X
 * 
 * 证明 (1) 算法的初始状态是安全的。
 * 
 * 算法的初始状态，待解集合R = \{(i, j), 0 <= i < j <= n-1
 * \}R={(i,j),0<=i<j<=n−1}，而正确状态一定位于R中。
 * 
 * 初始状态是安全的。
 * 
 * (2) 若第kk个状态是安全的，那么完成移动小指针操作得到的第k+1k+1个状态也是安全的。
 * 
 * 从状态kk到状态k+1k+1，算法完成了一次消去操作，
 * 
 * H[i] < H[j] ，完成的是消去X = \{(i, i+1), (i, i +2), ... , (i,
 * j)\}X={(i,i+1),(i,i+2),...,(i,j)}。 \forall (i, j')\in X , S(i, j') =
 * \begin{cases}H[i] * (j' - i) & \text{H[i] < H[j']}\\H[j'] *(j' - i) &
 * \text{H[i] > H[j']}\end{cases} ∀(i,j ′ )∈X,S(i,j ′ )={ H[i]∗(j ′ −i) H[j ′
 * ]∗(j ′ −i) ​
 * 
 * H[i] < H[j’] H[i] > H[j’] ​
 * 
 * 
 * 如果H[i] < H[j']，那么S(i, j') = H[i] * (j' - i) < H[i] *(j - i) = S(i, j )S(i,j ′
 * )=H[i]∗(j ′ −i)<H[i]∗(j−i)=S(i,j)，XX中的状态都小于S(i, j )S(i,j)。
 * 
 * 如果H[i] >= H[j'] ，那么S(i, j') = H[j'] * (j' - i) <= H[i] * (j' - i) < H[i] * (j
 * - i) = S(i, j)S(i,j ′ )=H[j ′ ]∗(j ′ −i)<=H[i]∗(j ′
 * −i)<H[i]∗(j−i)=S(i,j)，XX中的状态都小于S(i, j )S(i,j)。
 * 
 * 因此，H[i] < H[j]时，从R中消去X集合是安全的。
 * 
 * H[i] >= H[j] , 其实是与情形1对称的，边界等于归到任意一边不影响。 X = {(i, j), (i+1, j), ... , (j-1,
 * j)}X=(i,j),(i+1,j),...,(j−1,j)。考虑(i', j)(i ′ ,j)： 如果H[i'] < H[j] , 那么S(i', j)
 * = H[i'] * (j - i') < H[j] * (j - i') < H[j] * (j - i) =S(i, j)S(i ′ ,j)=H[i ′
 * ]∗(j−i ′ )<H[j]∗(j−i ′ )<H[j]∗(j−i)=S(i,j)
 * 
 * 如果H[i'] >= H[j]， 那么S(i', j) = H[j] * (j - i') < H[j] * (j - i) = S(i, j)S(i ′
 * ,j)=H[j]∗(j−i ′ )<H[j]∗(j−i)=S(i,j)
 * 
 * 因此，H[i] >= H[j]时，从RR中消去XX是安全的
 * 
 * 由1.2.可知，从RR中消去XX是安全的
 * 
 * 由(1)(2)可推知，算法的任一状态都是安全的。
 * 
 * 重新证明 其实对于状态0，算法计算了一个状态(0, n-1)(0,n−1)， 跳过了(n - 1 - 1)(n−1−1)个状态，
 * 
 * 对于状态1，算法计算了一个状态(1, n-1)或(0,n-2)(1,n−1)或(0,n−2)， 跳过了(n - 2 - 1)(n−2−1)个状态，
 * 
 * ……
 * 
 * 对于状态k,算法计算了一个状态(i,j)(i,j)，跳过了(n - (k + 1)- 1)(n−(k+1)−1)个状态，
 * 
 * ……
 * 
 * 当程序终止时，无论左右指针具体是如何移动的，只要每一步都移动小指针，那么程序终止时，一定移动了(n - 2)(n−2)步。
 * 
 * 那么一共计算了St = (n - 1)St=(n−1)个状态，跳过了Sp = (n - 1 - 1) + (n - 2 - 1) + …… + (n -
 * (n - 1) - 1) = 1 + 2 + …… +
 * (n-2)Sp=(n−1−1)+(n−2−1)+……+(n−(n−1)−1)=1+2+……+(n−2)个状态
 * 
 * 那么总共是Sum = Sp + St = 1 + 2 + …… + (n - 2) + (n - 1) =
 * n(n-1)/2Sum=Sp+St=1+2+……+(n−2)+(n−1)=n(n−1)/2
 * 
 * 而认真一想，所有这些状态，都没有重复的。(自行思考)
 * 
 * 那么，算法理论上应该经历多少状态？
 * 
 * 对于暴力法，实际上是从nn个数字里边挑两个构成一对的组合问题。因此一共有C_{n}^{2} = n(n-1)/2C n 2 ​ =n(n−1)/2对
 * 
 * 与双指针法实际考虑到的状态数是一样的，而双指针法并没有重复的考虑，因此，双指针法是正确的。
 * 
 * 图例
 * 
 * 
 * 作者：r3n4ive
 * 链接：https://leetcode-cn.com/problems/two-sum/solution/shuang-zhi-zhen-fa-zheng-que-xing-zheng-ming-by-r3/
 * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */