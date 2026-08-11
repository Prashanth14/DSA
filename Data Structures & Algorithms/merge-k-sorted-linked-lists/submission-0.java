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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        for(ListNode list: lists){
            result =  mergeTwoLists(result, list);
        }
        return result;
    }

     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(0); // placeholder head, simplifies edge cases
        ListNode node = dummy;             // tracks tail of the merged result

        // attach the smaller of the two current nodes, advance that list and node
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                node.next = list1;
                list1 = list1.next;
            }else{
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }

        // attach whichever list still has leftover nodes
        if(list1 != null){
            node.next = list1;
        }else{
            node.next = list2;
        }

        return dummy.next; // skip the placeholder, return actual merged head
    }
}
