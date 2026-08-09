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
    // Approach: HashMap mapping original node -> its deep copy
    // TC: O(n) - two linear passes over the list
    // SC: O(n) - HashMap stores all n original->copy node mappings
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        
        Node temp = head;
        Map<Node, Node> map = new HashMap<>(); // original node -> copied node

        // first pass: create a copy of every node (val only), map original -> copy
        while(temp != null){
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        // second pass: wire up next and random pointers on the copies,
        // using the map to translate original references into copy references.
        // map.get(null) safely returns null, so missing next/random is handled naturally.
        Node temp1 = head;
        while(temp1 != null){
            Node node = map.get(temp1);
            node.next = map.get(temp1.next);
            node.random = map.get(temp1.random);

            temp1 = temp1.next;
        }
        return map.get(head); // copied head
    }
}