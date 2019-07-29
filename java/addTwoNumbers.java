/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x){val = x;}
}

class Solution_2 {
    public static void main(String args[]){
        Solution_2 s = new Solution_2();

    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(-1);
        ListNode q = l;
        int flag = 0;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + flag;
            if(sum > 9){
                q.next = new ListNode(sum % 10);
                q = q.next;
                flag = 1;
            }else{
                q.next = new ListNode(sum);
                q = q.next;
                flag = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode rest = null;
        if(l1 != null){
            rest = l1;
        }else if( l2 != null){
            rest = l2;
        }
        while(rest != null){
            q.next = new ListNode(rest.val + flag);
            if(q.next.val > 9){
                q.next.val = 0;
                flag = 1;
            }else{
                flag = 0;
            }
            q = q.next;
            rest = rest.next;
        }
        if(flag == 1){
            q.next = new ListNode(1);
            q = q.next;
        }
        q.next = null;
        return l.next;

    }
}