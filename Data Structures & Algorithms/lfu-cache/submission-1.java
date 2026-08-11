// create Node class with key, value, frequency, prev and next pointers
class ListNode{
    int key;
    int val;
    int freq;
    ListNode prev;
    ListNode next;

    public ListNode(int key, int val){
        this.key = key;
        this.val = val;
        this.freq = 1;
        this.prev = null;
        this.next = null;
    }
}
// create DoubleLinkedList class with 
//insert at the end of list (right), 
//remove at head position(left) and
// remove at any given node
// and also Size
class DoublyLinkedList{
    ListNode left, right;
    int size;

    public DoublyLinkedList(){
        this.left = new ListNode(0, 0);
        this.right = new ListNode(0, 0);
        this.right.prev = this.left;
        this.left.next = this.right;
        this.size = 0;
    }

    public int length(){
        return size;
    }

    public void pushRight(ListNode node){
        ListNode prev = this.right.prev;
        prev.next = node;
        node.prev = prev;
        node.next = this.right;
        this.right.prev = node;
        size++;
    }

    public void pop(ListNode node){
        ListNode prev = node.prev;
        ListNode nxt = node.next;
        prev.next = nxt;
        nxt.prev= prev;
        node.next = null;
        node.prev = null;
        size--;
    }

    public ListNode popLeft(){
        ListNode lru = this.left.next;
        pop(lru);
        return lru;
    }
}

class LFUCache {
    private int capacity;
    private int lfuCount;
    private Map<Integer, ListNode> nodeMap;
    private Map<Integer, DoublyLinkedList> listMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.lfuCount  = 0;
        nodeMap = new HashMap<>();
        listMap = new HashMap<>();
    }

    public void counter(ListNode node){
        int count = node.freq;
        listMap.get(count).pop(node);

        if(count == lfuCount && listMap.get(count).length() == 0){
            lfuCount++;
        }

        node.freq++;
        listMap.putIfAbsent(node.freq, new DoublyLinkedList());
        listMap.get(node.freq).pushRight(node);
    }
    
    public int get(int key) {
        if(!nodeMap.containsKey(key)){
            return -1;
        }
        ListNode node = nodeMap.get(key);
        counter(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }

        if(nodeMap.containsKey(key)){
            ListNode node = nodeMap.get(key);
            node.val = value;
            counter(node);
            return;
        }

        if(nodeMap.size() == capacity){
            ListNode toRemove = listMap.get(lfuCount).popLeft();
            nodeMap.remove(toRemove.key);
        }

        ListNode node = new ListNode(key, value);
        nodeMap.put(key, node);
        listMap.putIfAbsent(1, new DoublyLinkedList());
        listMap.get(1).pushRight(node);
        lfuCount = 1;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */