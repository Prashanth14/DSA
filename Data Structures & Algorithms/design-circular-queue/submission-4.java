class MyCircularQueue {
    private int front;
    private int rear;
    private int n;
    private int size;
    private int[] cQueue;

    public MyCircularQueue(int k) {
        this.front = 0;
        this.rear = 0;
        this.n=k;
        this.size = 0;
        cQueue = new int[k];
    }
    
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        cQueue[rear] = value;
        rear = (rear + 1) % n;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % n;
        size--;
        return true;
    }
    
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return cQueue[front];
    }
    
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        int idx = (rear - 1 + n) % n;
        return cQueue[idx];
    }
    
    public boolean isEmpty() {
        if(size == 0) return true;
        return false;
    }
    
    public boolean isFull() {
        if(size == n) return true;
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