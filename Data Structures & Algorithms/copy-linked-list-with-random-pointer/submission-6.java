/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return head;

        Node temp = head;

        while(temp != null){
            Node newNode = new Node(temp.val);
            newNode.next = temp.next;
            newNode.random = temp.random;
            temp.next = newNode;

            temp = temp.next.next;
        }

        temp = head;
        while(temp != null){
            Node copy = temp.next;
            copy.random = (temp.random != null)? temp.random.next: null;
            temp = temp.next.next;
        }
        
        Node temp1 = head;
        Node temp2 = head.next;
        Node head2 = head.next;
        while(temp1 != null){
            temp1.next = temp1.next.next;
            temp1 = temp1.next;

            temp2.next = (temp2.next != null) ? temp2.next.next: null;
            temp2 = temp2.next;
        }
        return head2;

    }
}
