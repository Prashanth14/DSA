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
        //if(head == null) return null;
        Node temp = head;
        Map<Node, Node> map = new HashMap<>();

        while(temp != null){
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        Node temp1 = head;
        while(temp1 != null){
            Node node = map.get(temp1);
            node.next = map.get(temp1.next);
            node.random = map.get(temp1.random);

            temp1 = temp1.next;
        }
        return map.get(head);
    }
}
