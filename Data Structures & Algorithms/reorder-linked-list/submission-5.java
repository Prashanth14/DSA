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
    public void reorderList(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;

        while(temp != null){
            arr.add(temp.val);
            temp = temp.next;
        }

        int len = arr.size();
        int left = 0, right = len-1;
        ArrayList<Integer> result = new ArrayList<>();

        while(left <= right){
            result.add(arr.get(left));
            left++;
            result.add(arr.get(right));
            right--;
            // if(left <= right){
            //     result.add(arr.get(right));
            //     right--;
            // }
            
        }

        ListNode temp2 = head;
        for(int i = 0; i < len; i++){
            temp2.val = result.get(i);
            temp2 = temp2.next;
        }
    }
}
