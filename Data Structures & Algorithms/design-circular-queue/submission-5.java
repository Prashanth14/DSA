// Approach: circular array with front/rear pointers + size counter
// (size counter removes front==rear ambiguity between empty and full)
// TC: O(1) for every operation (enQueue, deQueue, Front, Rear, isEmpty, isFull)
// SC: O(n) for the underlying array (n = capacity k), O(1) extra space per operation
class MyCircularQueue {
    private int front;   // index of the oldest element (read end)
    private int rear;    // index of the NEXT free slot to write into
    private int n;        // capacity
    private int size;     // current number of elements
    private int[] cQueue;

    public MyCircularQueue(int k) {
        this.front = 0;
        this.rear = 0;
        this.n = k;
        this.size = 0;
        cQueue = new int[k];
    }

    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        cQueue[rear] = value;      // write at current rear (next free slot)
        rear = (rear + 1) % n;     // advance rear, wrap around if needed
        size++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % n;   // just advance front, wrap around if needed
                                     // (no need to clear the old value)
        size--;
        return true;
    }

    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return cQueue[front]; // front always points directly at the oldest element
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        // rear points one PAST the last written element, so the actual
        // last element is at (rear - 1), wrapped around safely with +n
        // to avoid a negative index when rear == 0
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