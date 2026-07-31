class MyHashSet {
    private LinkedList<Integer>[] buckets;
    private final int SIZE = 1000;

    public MyHashSet() {
        buckets = new LinkedList[SIZE];
        for(int i = 0; i< SIZE; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public int hash(int key){
        return key % SIZE;
    }
    
    public void add(int key) {
        int idx = hash(key);
        if(!buckets[idx].contains(key)){
            buckets[idx].add(key);
        }
    }
    
    public void remove(int key) {
        int idx = hash(key);
        buckets[idx].remove((Integer)key);
    }
    
    public boolean contains(int key) {
        int idx = hash(key);
        return buckets[idx].contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */