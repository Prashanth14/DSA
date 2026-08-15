class MyStack {
    // INVARIANT maintained by push(): after every push() call finishes,
    // q1 holds ALL current stack elements with the top of the stack at
    // the FRONT of the queue, and q2 is always left empty.
    // pop(), top(), and empty() all rely on this invariant being true.
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // O(n) — the expensive operation. Re-orders the queue so the newest
    // element ends up at the front (i.e., becomes the new "top").
    public void push(int x) {
        // Step 1: put the new element into q2 FIRST, before anything else.
        // This is what makes it end up at the front after the merge below.
        q2.offer(x);

        // Step 2: drain all of q1's existing elements into q2, behind x.
        // Since q1's front was the previous top, moving elements in
        // this order preserves correct relative stack order behind x.
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        // Step 3: swap references so q1 becomes the fully-loaded queue
        // (new top at front) and q2 goes back to being empty,
        // restoring the invariant for the next call.
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // O(1) — front of q1 is guaranteed (by the invariant) to be the
    // most recently pushed element, i.e., the top of the stack.
    public int pop() {
        return q1.poll();
    }

    // O(1) — same reasoning as pop(), but without removing it.
    public int top() {
        return q1.peek();
    }

    // O(1) — q2 is always empty except transiently mid-push, so q1 is
    // the only queue that ever actually holds elements between calls.
    // Checking q1 alone correctly tells you if the stack has anything.
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Core idea to remember on revisit:
 * A queue is FIFO (first-in-first-out), but a stack needs LIFO
 * (last-in-first-out). This class simulates LIFO behavior by paying
 * the reordering cost upfront on every push() — inserting the new
 * element first, then rotating all older elements behind it — so
 * that pop()/top()/empty() become trivial O(1) reads off the front
 * of q1, which always mirrors correct stack order.
 *
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */