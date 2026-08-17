class FreqStack {

    /*
     * Pattern:
     * HashMap + Frequency Buckets + Stack
     *
     * Goal:
     * pop() should return:
     * 1. The most frequent element
     * 2. If frequencies tie, the most recently pushed element
     *
     * We maintain 3 pieces of state:
     *
     * 1. freq:
     *    value -> current frequency
     *
     *    Example:
     *    5 -> 3
     *    7 -> 2
     *    4 -> 1
     *
     *
     * 2. mapGroupByFreq:
     *    frequency -> stack of values that reached that frequency
     *
     *    After pushes: 5, 7, 5, 7, 4, 5
     *
     *    1 -> [5, 7, 4]
     *    2 -> [5, 7]
     *    3 -> [5]
     *
     *    IMPORTANT:
     *    Values are NOT removed from lower-frequency stacks when
     *    their frequency increases.
     *
     *    Example:
     *    When 5 goes from freq 1 -> 2,
     *    it stays in frequency-1 stack and is also pushed into
     *    frequency-2 stack.
     *
     *    These older entries are intentional and preserve recency
     *    when maxFreq later decreases.
     *
     *
     * 3. maxFreq:
     *    Tracks the highest frequency currently present.
     *
     *
     * Why does tie-breaking work automatically?
     *
     * Each frequency group is a STACK.
     *
     * Example:
     * freq 2 -> [5, 7]
     *
     * If maxFreq = 2, both 5 and 7 have frequency 2,
     * but 7 reached frequency 2 more recently.
     *
     * Since 7 is on top, pop() correctly returns 7.
     *
     *
     * Time Complexity:
     *
     * push(): O(1) average
     * - HashMap get/put: O(1) average
     * - Stack push: O(1)
     *
     * pop(): O(1) average
     * - Directly access stack using maxFreq
     * - Stack pop: O(1)
     * - HashMap update: O(1) average
     *
     * Overall for N operations:
     * TC: O(N) average
     *
     *
     * Space Complexity:
     *
     * SC: O(N)
     *
     * Even though a value can appear in multiple frequency stacks,
     * every push operation adds exactly one entry to one stack.
     * Therefore across N pushes, total stored stack entries are O(N).
     *
     * freq map also stores at most O(N) distinct values.
     */

    // value -> current frequency
    Map<Integer, Integer> freq;

    // frequency -> stack of values that reached this frequency
    Map<Integer, Stack<Integer>> mapGroupByFreq;

    // Highest frequency currently present
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        mapGroupByFreq = new HashMap<>();

        // Initially there are no elements.
        // 0 is slightly more natural here than -1.
        maxFreq = 0;
    }

    public void push(int val) {

        // Increase frequency of this value.
        //
        // Example:
        // First push(5):  0 + 1 = 1
        // Second push(5): 1 + 1 = 2
        freq.put(val, freq.getOrDefault(val, 0) + 1);

        int currentFreq = freq.get(val);

        /*
         * Create a new stack only if this frequency
         * has never been seen before.
         *
         * Example:
         * currentFreq = 2
         *
         * If group 2 doesn't exist:
         * 2 -> []
         */
        if (!mapGroupByFreq.containsKey(currentFreq)) {
            mapGroupByFreq.put(currentFreq, new Stack<>());
        }

        /*
         * Push val into the stack corresponding to the
         * frequency it has just reached.
         *
         * Example:
         *
         * push(5), push(7), push(5), push(7)
         *
         * freq 1 -> [5, 7]
         * freq 2 -> [5, 7]
         *
         * Stack order automatically preserves recency.
         */
        mapGroupByFreq.get(currentFreq).push(val);

        // Update the highest frequency seen currently.
        maxFreq = Math.max(maxFreq, currentFreq);
    }

    public int pop() {

        /*
         * The most frequent element must be inside
         * the stack corresponding to maxFreq.
         *
         * The top of that stack is also the most recent
         * among elements tied at maxFreq.
         */
        Stack<Integer> maxFreqStack = mapGroupByFreq.get(maxFreq);

        // Remove the correct element.
        int val = maxFreqStack.pop();

        // Since one occurrence was removed,
        // decrease its current frequency.
        freq.put(val, freq.get(val) - 1);

        /*
         * If no elements remain at the current max frequency,
         * then the next highest possible frequency is maxFreq - 1.
         */
        if (maxFreqStack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 *
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */