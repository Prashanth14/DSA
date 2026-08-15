class MinStack {
    // st: the actual stack — holds every value in push order, same as a normal stack.
    private Stack<Integer> st;

    // minStack: a parallel "running minimum" tracker.
    // INVARIANT: minStack.peek() always equals the current minimum of everything in st.
    private Stack<Integer> minStack;

    public MinStack() {
        st = new Stack<>();
        minStack = new Stack<>();
    }

    // O(1) — push onto st always; push onto minStack only when val could BE
    // (or tie) the new minimum.
    public void push(int val) {
        st.push(val);

        if (minStack.isEmpty()) {
            // First element ever — it's trivially the current minimum.
            minStack.push(val);
        } else {
            // IMPORTANT: use <= (not <) here. If val equals the current min,
            // push it anyway. This keeps one minStack entry per occurrence
            // of the minimum value in st, so that popping one duplicate
            // later doesn't wrongly make minStack "forget" the min still
            // exists elsewhere in st.
            if (val <= minStack.peek()) {
                minStack.push(val);
            }
        }
    }

    // O(1) — pop from st, and keep minStack in sync if the popped value
    // WAS the current minimum.
    public void pop() {
        if (st.isEmpty()) return;

        int top = st.pop();

        // If the value we just removed from st was the current min,
        // remove its corresponding entry from minStack too — otherwise
        // minStack would keep reporting a minimum that's no longer in st.
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }

    // O(1) — top of st is always the most recently pushed value.
    public int top() {
        return st.peek();
    }

    // O(1) — by the invariant, minStack's top is always the current minimum
    // of everything currently in st. No scanning needed.
    public int getMin() {
        return minStack.peek();
    }
}