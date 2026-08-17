class FreqStack {
    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> mapGroupByFreq;
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        mapGroupByFreq = new HashMap<>();
        maxFreq = -1;
    }
    
    public void push(int val) {
        freq.put(val, freq.getOrDefault(val, 0)+1);
        int currentFreq = freq.get(val);

        if(!mapGroupByFreq.containsKey(currentFreq)){
            mapGroupByFreq.put(currentFreq, new Stack<>());
        }
        mapGroupByFreq.get(currentFreq).push(val);

        maxFreq = Math.max(maxFreq, currentFreq);
    }
    
    public int pop() {
        Stack<Integer> maxFreqStack = mapGroupByFreq.get(maxFreq);
        int val = maxFreqStack.pop();

        freq.put(val, freq.get(val)-1);

        if(maxFreqStack.isEmpty()){
            maxFreq--;
        }
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */