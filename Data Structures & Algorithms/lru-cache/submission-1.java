class ListNode{
    int val;
    int key;
    ListNode next;
    ListNode prev;

    public ListNode(int _key, int _val){
        this.val = _val;
        this.key = _key;
        this.next = null;
        this.prev = null;
    }
}
class LRUCache {
    int capacity;
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> map = new HashMap<>();

    public void insertAtHead(ListNode node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    public ListNode deleteNodeAtTail(){
        ListNode dNode = tail.prev;
        dNode.prev.next = tail;
        tail.prev = dNode.prev;
        return dNode;
    }

    public void deleteNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);

        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){ // if map doesn't contains then return -1;
            return -1;
        }
        ListNode node = map.get(key);
        deleteNode(node);
        insertAtHead(node);
        map.put(key, head.next); // this node will be the fist node after the dummy head node
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            node.val = value;
            deleteNode(node);
            insertAtHead(node);
            map.put(key, head.next);
        }else if(!map.containsKey(key)){ // if map contains the key
            if(map.size() == capacity){ // if cache is full
                ListNode dNode = deleteNodeAtTail();
                map.remove(dNode.key); // this is the reason we store key in the LinkedList along with the value
            }
           ListNode newNode = new ListNode(key, value);
           insertAtHead(newNode);
           map.put(key, head.next);
        }
    }
}
