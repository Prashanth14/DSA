class MyHashMap {
    // Direct addressing: key itself is the array index.
    // -1 = sentinel for "no mapping" (value is always >= 0, so safe).
    // TC: O(1) for put/get/remove
    // SC: O(1,000,001) fixed, regardless of how many keys actually used
    int[] arr;

    public MyHashMap() {
        arr = new int[1000001];
        Arrays.fill(arr, -1); // mark every slot as empty initially
    }

    public void put(int key, int value) {
        arr[key] = value; // insert or overwrite
    }

    public int get(int key) {
        if (arr[key] != -1) {
            return arr[key]; // found
        }
        return -1; // not present
    }

    public void remove(int key) {
        arr[key] = -1; // mark as empty again
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */