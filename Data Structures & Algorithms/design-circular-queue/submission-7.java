class Node{
    int val;
    Node next;

    public Node(int val){
        this.val = val;
        this.next = null;
    }
}

// Approach: singly linked list with dummy head (left) + tail pointer (right)
// 'size' tracks REMAINING FREE SPACE (not element count) - decrements on
// enQueue, increments on deQueue, full when 0, empty when back to n.
// TC: O(1) for every operation (enQueue, deQueue, Front, Rear, isEmpty, isFull)
// SC: O(n) for the linked list nodes (n = capacity k), O(1) extra per operation
class MyCircularQueue {
    Node left;   // dummy head sentinel; left.next is the actual front element
    Node right;  // tail pointer, points to the last real node (or left if empty)
    private int n;
    private int size; // remaining free space

    public MyCircularQueue(int k) {
        this.size = k;      // start with full capacity available
        this.n = k;
        this.left = new Node(0);   // dummy node, holds no real data
        this.right = this.left;    // initially tail == dummy head (empty queue)
    }

    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }

        Node newNode = new Node(value);
        if(isEmpty()){
            // first real node: link dummy head directly to it
            this.left.next = newNode;
            this.right = newNode;
        }else{
            // append after current tail
            this.right.next = newNode;
            this.right = newNode;
        }
        this.size--; // used up one slot
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        // unlink the front node by skipping over it
        this.left.next = (this.left.next != null) ? this.left.next.next : null;

        // if that removal emptied the list, reset tail back to dummy head
        // (otherwise 'right' would dangle, pointing at the removed node)
        if(this.left.next == null){
            this.right = this.left;
        }

        this.size++; // freed up one slot
        return true;
    }

    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return left.next.val; // front is always the node right after dummy head
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return right.val; // tail pointer always points at the last element
    }

    public boolean isEmpty() {
        return this.size == n; // all capacity still free = no elements
    }

    public boolean isFull() {
        return this.size == 0; // no free space left
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */