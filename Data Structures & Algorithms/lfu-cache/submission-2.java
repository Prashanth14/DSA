// ============================================================================
// LFU CACHE  (LeetCode 460)
// ----------------------------------------------------------------------------
// Approach: HashMap of (key -> node)  +  HashMap of (frequency -> DoublyLinkedList of nodes)
// Every frequency bucket is its own DLL, ordered by recency (head = LRU, tail = MRU).
// "lfuCount" always points to the bucket holding the least-frequently-used node(s),
// so eviction is O(1): pop the head of listMap.get(lfuCount).
//
// Overall Time Complexity : O(1) for get() and put()
// Overall Space Complexity: O(capacity) -> nodeMap + listMap together hold at most
//                           `capacity` ListNodes total, spread across frequency buckets.
// ============================================================================
 
// create Node class with key, value, frequency, prev and next pointers
class ListNode {
    int key;
    int val;
    int freq;
    ListNode prev;
    ListNode next;
 
    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;          // every new node starts at frequency 1
        this.prev = null;
        this.next = null;
    }
    // TC: O(1)   SC: O(1)
}
 
// create DoubleLinkedList class with
// insert at the end of list (right),
// remove at head position (left) and
// remove at any given node
// and also Size
// NOTE: uses two dummy sentinel nodes (left, right) so insert/remove never
// need null-checks -> real nodes always live strictly between left and right.
class DoublyLinkedList {
    ListNode left, right;
    int size;
 
    public DoublyLinkedList() {
        this.left = new ListNode(0, 0);   // dummy head sentinel
        this.right = new ListNode(0, 0);  // dummy tail sentinel
        this.right.prev = this.left;
        this.left.next = this.right;
        this.size = 0;
        // TC: O(1)   SC: O(1)
    }
 
    // returns number of real nodes currently in this list
    public int length() {
        return size;
        // TC: O(1)   SC: O(1)
    }
 
    // insert node just before the right sentinel -> marks it "most recently used"
    public void pushRight(ListNode node) {
        ListNode prev = this.right.prev;
        prev.next = node;
        node.prev = prev;
        node.next = this.right;
        this.right.prev = node;
        size++;
        // TC: O(1)   SC: O(1)
    }
 
    // detach an arbitrary node from the list (used both for eviction and for
    // moving a node to its next frequency bucket)
    public void pop(ListNode node) {
        ListNode prev = node.prev;
        ListNode nxt = node.next;
        prev.next = nxt;
        nxt.prev = prev;
        node.next = null;
        node.prev = null;
        size--;
        // TC: O(1)   SC: O(1)
    }
 
    // remove and return the node right after the left sentinel -> the
    // least-recently-used node in this frequency bucket
    public ListNode popLeft() {
        ListNode lru = this.left.next;
        pop(lru);
        return lru;
        // TC: O(1)   SC: O(1)
    }
}
 
class LFUCache {
    private int capacity;
    private int lfuCount;                          // current minimum frequency in the cache
    private Map<Integer, ListNode> nodeMap;         // key            -> node   (O(1) lookup)
    private Map<Integer, DoublyLinkedList> listMap; // frequency      -> DLL of nodes at that freq
 
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.lfuCount = 0;
        nodeMap = new HashMap<>();
        listMap = new HashMap<>();
        // TC: O(1)   SC: O(1)
    }
 
    // Bumps a node's frequency by 1 and relocates it to the correct bucket.
    // Called on every successful get() and every put() on an existing key.
    public void counter(ListNode node) {
        int count = node.freq;
        listMap.get(count).pop(node);          // remove node from its current freq bucket
 
        // if this node was the LAST one at the current minimum frequency,
        // that bucket is now empty -> the global minimum frequency must rise by 1
        if (count == lfuCount && listMap.get(count).length() == 0) {
            lfuCount++;
        }
 
        node.freq++;                                        // bump frequency
        listMap.putIfAbsent(node.freq, new DoublyLinkedList()); // create bucket if new
        listMap.get(node.freq).pushRight(node);              // re-insert as most-recently-used
        // TC: O(1)   SC: O(1)
    }
 
    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        ListNode node = nodeMap.get(key);
        counter(node);      // accessing a key counts as a "use" -> bump frequency
        return node.val;
        // TC: O(1)   SC: O(1)
    }
 
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
 
        // Case 1: key already exists -> just update value and bump frequency
        if (nodeMap.containsKey(key)) {
            ListNode node = nodeMap.get(key);
            node.val = value;
            counter(node);
            return;
        }
 
        // Case 2: cache is full -> evict the LRU node from the LFU bucket
        if (nodeMap.size() == capacity) {
            ListNode toRemove = listMap.get(lfuCount).popLeft();
            nodeMap.remove(toRemove.key);
        }
 
        // Case 3: insert brand-new node at frequency 1
        ListNode node = new ListNode(key, value);
        nodeMap.put(key, node);
        listMap.putIfAbsent(1, new DoublyLinkedList());
        listMap.get(1).pushRight(node);
        lfuCount = 1;   // any freshly inserted node resets the global minimum to 1
        // TC: O(1)   SC: O(1)  (amortized; capacity-bounded)
    }
}
 
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
// ============================================================================
// QUICK REVISION CHEAT SHEET
// ----------------------------------------------------------------------------
// Data structures:
//   nodeMap  : key -> ListNode                (O(1) direct access to any node)
//   listMap  : frequency -> DoublyLinkedList   (each bucket ordered by recency)
//   lfuCount : smallest frequency currently present in the cache
//
// Why DLL per frequency instead of one global list?
//   Lets us evict in O(1): always pop-left from listMap.get(lfuCount).
//
// Why sentinel (dummy) head/tail nodes in DLL?
//   Avoids null checks on push/pop -> every real node always has a valid prev/next.
//
// counter() invariant to remember:
//   1. pop node from old freq bucket
//   2. if that bucket is now empty AND was the min bucket -> lfuCount++
//   3. freq++, push node into (new) freq bucket on the right (MRU end)
//
// put() invariant to remember:
//   - existing key  -> update val, counter()
//   - full capacity -> evict popLeft() from listMap.get(lfuCount) bucket
//   - new key       -> insert at freq 1, reset lfuCount = 1
//
// Overall: O(1) get, O(1) put, O(capacity) space.
// ============================================================================