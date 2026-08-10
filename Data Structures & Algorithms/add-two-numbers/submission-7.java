/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        int sum = 0;

        while(t1 != null && t2 != null){
            sum += t1.val + t2.val;
            
            if(sum > 9){
                int rem = sum % 10;
                t1.val = rem;
                sum = sum / 10; 
            }else{
                t1.val = sum;
                sum = 0;
            }
            t1 = t1.next;
            t2 = t2.next;
        }

        while(t1 != null){
             sum += t1.val;
             if(sum > 9){
                int rem = sum % 10;
                t1.val = rem;
                sum = sum / 10; 
            }else{
                t1.val = sum;
                sum = 0;
            }
            t1 = t1.next;
        }

        ListNode t3 = l1;
        while(t3.next != null){
            t3 = t3.next;
        }

        while(t2 != null){
             sum += t2.val;
             ListNode newNode = new ListNode();
             if(sum > 9){
                int rem = sum % 10;
                newNode.val = rem;
                t3.next = newNode;
                t3 = t3.next;
                sum = sum / 10; 
            }else{
                newNode.val = sum;
                t3.next = newNode;
                t3 = t3.next;
                sum = 0;

            }
            t2 = t2.next;
        }

        if(sum > 0){
            ListNode newNode = new ListNode(sum);
            ListNode temp = l1;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
        return l1;
    }
}
