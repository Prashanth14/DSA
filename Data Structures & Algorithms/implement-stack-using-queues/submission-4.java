class MyStack {
    // INVARIANT: after every push() call, q's FRONT always holds the
    // most recently pushed element (i.e., the current top of the stack),
    // and elements are ordered front-to-back as top-to-bottom of the stack.
    // pop(), top(), and empty() all rely on this being true.
    Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<>();
    }

    // O(n) — the expensive operation. Adds x, then rotates it to the front.
    public void push(int x) {
        // Step 1: add the new element to the back of the queue (normal FIFO insert).
        q.offer(x);

        // Step 2: rotate the queue (size - 1) times — poll the front and
        // immediately offer it back to the back. This pushes every OLD
        // element behind x one at a time, until x is the only one left
        // that hasn't been rotated, meaning x is now at the front.
        // Example: push(3) on [2,1,x] -> rotate twice -> [x,2,1]... 
        // wait, trace concretely: q=[2,1,3] (x=3 just added at back).
        //   rotate 1: poll 2, offer 2  -> q=[1,3,2]
        //   rotate 2: poll 1, offer 1  -> q=[3,2,1]  <- x=3 now at front (top)
        for (int i = q.size() - 1; i > 0; i--) {
            q.offer(q.poll());
        }
    }

    // O(1) — front of q is guaranteed (by the invariant) to be the top of the stack.
    public int pop() {
        return q.poll();
    }

    // O(1) — same reasoning as pop(), but without removing it.
    public int top() {
        return q.peek();
    }

    // O(1) — stack has elements iff the underlying queue has elements.
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Core idea to remember on revisit:
 * A single queue can simulate a stack (LIFO) by paying a rotation cost
 * upfront on every push(): insert normally at the back, then rotate the
 * queue just enough times (size - 1) to walk the new element all the way
 * around to the front. Once it's at the front, pop()/top()/empty() are
 * free O(1) reads — no second queue needed, unlike the two-queue version.
 *
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */