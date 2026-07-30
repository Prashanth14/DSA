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
        //use HashMpa to store the deep copy nodes first
        Node temp = head;

        HashMap<Node, Node> map = new HashMap<>();
        while(temp != null){
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
       
        while(temp != null){
            Node dCopy = map.get(temp);
            dCopy.next = map.get(temp.next);
            dCopy.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }
}
