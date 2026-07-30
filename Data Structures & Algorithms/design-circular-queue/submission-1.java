//Solution 2 using Single LinkedList
class ListNode{
    int val;
    ListNode next;

    public ListNode(int _val){
        this.val = _val;
        this.next = null;
    }
}
class MyCircularQueue {
    int space;
    ListNode front;
    ListNode rear; // initially the front and rear of the circular Queue will be the same
    public MyCircularQueue(int k) {
        this.space = k;
        front = new ListNode(0);
        rear = this.front; // initially the front and rear of the circular Queue will be the same
    }
    
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }

        ListNode newNode = new ListNode(value);
        if(isEmpty()){
          this.front.next = newNode;
          this.rear = newNode;
        }else{
            this.rear.next = newNode;
            this.rear = newNode;
        }
        this.space--;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        this.front.next = this.front.next.next;
        if(this.front.next == null){
            this.rear = this.front;
        }
        this.space++;
        return true;
    }
    
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return this.front.next.val;
    }
    
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return this.rear.val;
    }
    
    public boolean isEmpty() {
        if(this.front.next == null){
            return true;
        }
        return false;
    }
    
    public boolean isFull() {
        if(this.space == 0){
            return true;
        }
        return false;
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