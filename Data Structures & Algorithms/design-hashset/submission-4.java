class MyHashSet {
    // Bucket array + chaining. Each bucket is a list holding keys
    // that hashed to the same index (handles collisions).
    // TC: O(n/SIZE) avg per op -> ~O(1) if keys spread evenly
    // SC: O(SIZE + n) -> buckets array + elements actually stored
    private LinkedList<Integer>[] buckets;
    private final int SIZE = 1000;

    public MyHashSet() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>(); // init each bucket as empty list
        }
    }

    // Maps any key to a bucket index in [0, SIZE-1].
    public int hash(int key) {
        return key % SIZE;
    }

    public void add(int key) {
        int idx = hash(key);
        if (!buckets[idx].contains(key)) { // avoid duplicate entries
            buckets[idx].add(key);
        }
    }

    public void remove(int key) {
        int idx = hash(key);
        buckets[idx].remove((Integer) key); // remove by VALUE, not index
    }

    public boolean contains(int key) {
        int idx = hash(key);
        return buckets[idx].contains(key); // search this bucket only
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */