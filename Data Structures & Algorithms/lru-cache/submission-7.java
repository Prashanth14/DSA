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

class LRUCache {
    private int capacity;
    Map<Integer, Node> cache;
    Node left = null;
    Node right = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.left = new Node(0,0);
        this.right = new Node(0, 0);
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public void remove(Node node){
        Node prev = node.prev;
        Node nxt = node.next;

        nxt.prev = prev;
        prev.next = nxt;
    }

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
            remove(node);
            insert(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            remove(node);
            cache.remove(key);
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);

        if(cache.size() > capacity){
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }

    }
}
