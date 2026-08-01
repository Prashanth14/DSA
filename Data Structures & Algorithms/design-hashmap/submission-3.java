class MyHashMap {
    // Bucket array + chaining. Each bucket is a list of {key, value}
    // pairs (int[2]) for keys that hashed to the same index.
    // TC: O(n/SIZE) avg per op -> ~O(1) if keys spread evenly
    // SC: O(SIZE + n) -> buckets array + pairs actually stored
    private LinkedList<int[]>[] buckets;
    private final int SIZE = 1000;

    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>(); // init each bucket as empty list
        }
    }

    // Maps any key to a bucket index in [0, SIZE-1].
    public int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int idx = hash(key);
        for (int[] pair : buckets[idx]) {
            if (pair[0] == key) {
                pair[1] = value; // key already exists -> update value
                return;
            }
        }
        buckets[idx].add(new int[]{key, value}); // key not found -> insert new pair
    }

    public int get(int key) {
        int idx = hash(key);
        for (int[] pair : buckets[idx]) {
            if (pair[0] == key) {
                return pair[1]; // found
            }
        }
        return -1; // not found
    }

    public void remove(int key) {
        int idx = hash(key);
        // Iterator used instead of for-each: modifying a list mid-loop
        // with for-each throws ConcurrentModificationException.
        // it.remove() is the safe way to delete during iteration.
        Iterator<int[]> it = buckets[idx].iterator();
        while (it.hasNext()) {
            int[] pair = it.next();
            if (pair[0] == key) {
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