class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if(empty()){
            return -1;
        }
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                int s1Ele = s1.pop();
                s2.push(s1Ele);
            }
        }
        int popEle = s2.pop();
        return popEle;
    }
    
    public int peek() {
        if(empty()){
            return -1;
        }
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                int s1Ele = s1.pop();
                s2.push(s1Ele);
            }
        }

        int peekEle = s2.peek();
        return peekEle;
    }
    
    public boolean empty() {
        if(s2.isEmpty()){
            if(s1.isEmpty()){
                return true;                
            }
        }
        return false;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */