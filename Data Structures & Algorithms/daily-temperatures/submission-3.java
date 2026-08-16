class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;

        // res[i] = number of days to wait for a warmer temperature.
        // Defaults to 0 (Java auto-inits int arrays), which correctly
        // represents "no warmer day ever comes" if never updated.
        int[] res = new int[len];

        // Monotonic DECREASING stack: stores {temperature, index} pairs
        // for days we haven't found a warmer future day for YET.
        // Stack stays sorted so temps decrease from bottom to top —
        // i.e., whenever we're about to push something that breaks that
        // order (a warmer day), we resolve everything smaller first.
        Stack<int[]> st = new Stack<>();

        for (int i = 0; i < len; i++) {
            int t = temperatures[i];

            // While today's temp is WARMER than the temp on top of the
            // stack, today IS the "next warmer day" for that stacked day.
            // Pop it and record the gap. Keep doing this — today might
            // resolve several previous colder days in a row.
            while (!st.isEmpty() && t > st.peek()[0]) {
                int[] pair = st.pop();          // pair = {temp, originalIndex} of a still-unresolved earlier day
                res[pair[1]] = i - pair[1];     // gap in days between that day and today
            }

            // Today itself hasn't found its warmer day yet (nothing ahead
            // has been checked), so push it onto the stack to wait.
            st.push(new int[]{t, i});
        }

        // Anything still left in the stack at the end never found a
        // warmer day — their res[] entries correctly remain 0 (default).
        return res;
    }
}