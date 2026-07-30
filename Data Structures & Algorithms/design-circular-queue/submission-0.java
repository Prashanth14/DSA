//Solution 1 using dynamic Arrays
class MyCircularQueue {
    List<Integer> cQueue;
    int capacity;
    public MyCircularQueue(int k) {
        cQueue = new ArrayList<>();
        capacity = k;
    }
    
    public boolean enQueue(int value) {
        if(cQueue.size() == capacity){ // if Circular Queue is full then return false
            return false;
        }
        cQueue.add(value);
        return true;
    }
    
    public boolean deQueue() {
        if(cQueue.isEmpty()){ // if Circular Queue is Emoty then there is nothing to delete so return false
            return false;
        }
        cQueue.remove(0);
        return true;
    }
    
    public int Front() {
        if(cQueue.isEmpty()){
            return -1;
        }
        return cQueue.get(0);
    }
    
    public int Rear() {
        if(cQueue.isEmpty()){
            return -1;
        }
        return cQueue.get(cQueue.size()-1);
    }
    
    public boolean isEmpty() {
        if(cQueue.size() == 0){
            return true;
        }
        return false;
    }
    
    public boolean isFull() {
        if(cQueue.size() == capacity){
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