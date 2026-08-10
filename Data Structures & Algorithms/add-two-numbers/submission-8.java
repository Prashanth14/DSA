class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1, t2 = l2;
        ListNode tail = l1; // always trails behind, marking the last real node of l1
        int carry = 0;

        // phase 1: add corresponding digits while both lists still have nodes
        while (t1 != null && t2 != null) {
            int sum = t1.val + t2.val + carry;
            t1.val = sum % 10;
            carry = sum / 10;
            tail = t1;
            t1 = t1.next;
            t2 = t2.next;
        }

        // phase 2: propagate remaining carry through leftover l1 nodes
        while (t1 != null) {
            int sum = t1.val + carry;
            t1.val = sum % 10;
            carry = sum / 10;
            tail = t1;
            t1 = t1.next;
        }

        // phase 3: append leftover l2 nodes (with carry) to the end of l1
        while (t2 != null) {
            int sum = t2.val + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            t2 = t2.next;
        }

        // phase 4: any final leftover carry becomes one last new node
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return l1;
    }
}