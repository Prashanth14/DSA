class MyHashMap {
    private LinkedList<int[]>[] buckets;
    private final int SIZE = 1000;

    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for(int i = 0; i< SIZE; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public int hash(int key){
        return key % SIZE;
    }
    
    public void put(int key, int value) {
        int idx = hash(key);
        for(int[] pair: buckets[idx]){
            if(pair[0] == key){
                pair[1] = value;
                return;
            }
        }
        buckets[idx].add(new int[]{key, value});
    }
    
    public int get(int key) {
        int idx = hash(key);
        for(int[] pair: buckets[idx]){
            if(pair[0] == key){
                return pair[1];
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int idx = hash(key);
        Iterator<int[]> it = buckets[idx].iterator();
        while(it.hasNext()){
            int[] pair = it.next();
            if(pair[0] == key){
                it.remove();
                return;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */