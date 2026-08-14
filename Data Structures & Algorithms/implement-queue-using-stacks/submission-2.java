class MyQueue {
    // s1 = "inbox" stack: newly pushed elements always land here first.
    Stack<Integer> s1;
    // s2 = "outbox" stack: holds elements in reversed (FIFO-ready) order for pop/peek.
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // O(1) — push always goes to s1, no reordering needed yet.
    public void push(int x) {
        s1.push(x);
    }

    // O(1) amortized, O(n) worst case.
    public int pop() {
        if (empty()) {
            return -1;
        }
        // Only refill s2 when it's empty — this is the key rule.
        // If s2 still has elements, they are already in the correct
        // FIFO order, so moving s1 into it now would let newer
        // elements jump ahead of older ones (breaks queue order).
        if (s2.isEmpty()) {
            // Reversing s1 into s2 flips the order: the oldest
            // pushed element (bottom of s1) ends up on top of s2.
            while (!s1.isEmpty()) {
                int s1Ele = s1.pop();
                s2.push(s1Ele);
            }
        }
        // Top of s2 is always the oldest remaining element -> FIFO.
        int popEle = s2.pop();
        return popEle;
    }

    // Same logic as pop(), just reads instead of removes.
    public int peek() {
        if (empty()) {
            return -1;
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                int s1Ele = s1.pop();
                s2.push(s1Ele);
            }
        }
        int peekEle = s2.peek();
        return peekEle;
    }

    // O(1) — queue is empty only if both stacks are empty.
    public boolean empty() {
        if (s2.isEmpty()) {
            if (s1.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Core idea to remember:
 * - s1 = write side, s2 = read side.
 * - Never move elements from s1 to s2 unless s2 is empty.
 *   That "only refill when empty" rule is what guarantees
 *   each element moves at most twice total (once in, once
 *   across) -> amortized O(1) per operation.
 *
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */