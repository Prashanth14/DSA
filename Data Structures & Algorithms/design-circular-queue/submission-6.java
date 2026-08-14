class Node{
    int val;
    Node next;

    public Node(int val){
        this.val = val;
        this.next = null;
    }
}

class MyCircularQueue {
    Node left;
    Node right;
    private int n;
    private int size;

    public MyCircularQueue(int k) {
        this.size = k;
        this.n = k;
        this.left = new Node(0);
        this.right = this.left;
    }
    
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }

         Node newNode = new Node(value);
        if(isEmpty()){
            this.left.next = newNode;
            this.right = newNode;
        }else{
            this.right.next = newNode;
            this.right = newNode;
        }
        
        this.size--;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        this.left.next = (this.left.next != null) ? this.left.next.next : null;
        if(this.left.next == null){
            this.right = this.left;
        }

        this.size++;
        return true;
    }
    
    public int Front() {
         if(isEmpty()){
            return -1;
        }
        return left.next.val;
    }
    
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return right.val;
    }
    
    public boolean isEmpty() {
        return this.size == n;
    }
    
    public boolean isFull() {
        return this.size == 0;
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