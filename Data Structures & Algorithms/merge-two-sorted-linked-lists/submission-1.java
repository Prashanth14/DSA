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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ArrayList<Integer> arr = new ArrayList<>();
        while(temp1 != null){
            arr.add(temp1.val);
            temp1 = temp1.next;
        }
         while(temp2 != null){
            arr.add(temp2.val);
            temp2 = temp2.next;
        }
        
        Collections.sort(arr);

        ListNode res = null;
        ListNode temp = null;
        for(int it : arr){
            ListNode newNode = new ListNode(it);
            if(res == null){
                res = newNode;
                temp = res;
            }else{
                temp.next = newNode;
                temp = temp.next;
            }

        }
        return res;
    }
}