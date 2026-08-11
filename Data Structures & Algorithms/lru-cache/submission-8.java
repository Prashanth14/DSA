class Node{
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val){
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

// Approach: HashMap (O(1) key lookup) + Doubly Linked List (O(1) reorder/evict)
// left  = dummy head sentinel -> list runs LRU (least recently used) side
// right = dummy tail sentinel -> list runs MRU (most recently used) side
// Order in list: left <-> [LRU ... MRU] <-> right
// TC: O(1) for both get() and put() - all operations are direct pointer
//     manipulation or HashMap access, no traversal needed
// SC: O(capacity) - map and list together hold at most 'capacity' nodes
class LRUCache {
    private int capacity;
    Map<Integer, Node> cache; // key -> Node, for O(1) lookup
    Node left = null;  // dummy node marking the LRU end
    Node right = null; // dummy node marking the MRU end

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.left = new Node(0,0);
        this.right = new Node(0, 0);
        // link the two sentinels together to form an empty list initially
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    // unlink a node from wherever it currently sits in the list
    public void remove(Node node){
        Node prev = node.prev;
        Node nxt = node.next;

        nxt.prev = prev;
        prev.next = nxt;
    }

    // insert a node right before 'right' sentinel -> marks it as most recently used
    public void insert(Node node){
        Node prev = this.right.prev;
        prev.next = node;
        node.next = this.right;
        node.prev = prev;
        this.right.prev = node;
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            // accessing this node makes it most recently used:
            // remove from current position, reinsert at MRU end
            remove(node);
            insert(node);
            return node.val;
        }
        return -1; // key not found
    }
    
    public void put(int key, int value) {
        // if key already exists, remove old node from both list and map
        // before inserting the updated version (avoids stale/duplicate nodes)
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            remove(node);
            cache.remove(key);
        }

        // insert the new/updated node as most recently used
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);

        // over capacity: evict the least recently used node (left.next),
        // which is the node right after the left sentinel
        if(cache.size() > capacity){
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }
}