class MinStack {
    private Stack<Integer> st;
    private Stack<Integer> minStack;

    public MinStack() {
        st = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        st.push(val);
        if(minStack.isEmpty()){
            minStack.push(val);
        }else{
            if(val <= minStack.peek()){
                minStack.push(val);
            }
        }
    }
    
    public void pop() {
        if(st.isEmpty()) return;
        int top = st.pop();

        if (top == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
