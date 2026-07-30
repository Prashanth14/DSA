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
        Node temp = head;
        //Step 1 create new copied nodes and insert in between the Original nodes
        while(temp != null){
            Node newNode = new Node(temp.val);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = newNode.next;
        }

        //Step 2: copy random pointers to copy nodes
        temp = head;
        while(temp != null){
           if(temp.random != null){
            temp.next.random = temp.random.next;
           }
           temp = temp.next.next;
        }

        //Step 3; change the next pointer
        Node dummy = new Node(-1);
        Node res = dummy;
        temp = head;
        while(temp != null){
            dummy.next = temp.next;
            temp.next = temp.next.next;
            dummy = dummy.next;
            temp = temp.next;
        }

        return res.next;
    }
}
