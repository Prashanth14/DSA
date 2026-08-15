class Solution {
    public int calPoints(String[] operations) {
        int len = operations.length;
        if (len == 0) return 0;

        // Stack holds the current valid record of scores, in order.
        // Top of stack = most recent score.
        Stack<Integer> st = new Stack<>();

        for (String s : operations) {

            // Not one of the three special operators -> it's a plain number string.
            if (!s.equals("+") && !s.equals("C") && !s.equals("D")) {
                int n = Integer.parseInt(s);
                st.push(n);

            } else {

                if (s.equals("D")) {
                    // Double the previous score and record it as a NEW score.
                    // Previous score stays on the record — only peek, don't remove it.
                    int top = st.pop();
                    st.push(top);       // put it back, unchanged
                    st.push(top * 2);   // new doubled score goes on top

                } else if (s.equals("C")) {
                    // Invalidate (permanently remove) the most recent score.
                    st.pop();

                } else if (s.equals("+")) {
                    // New score = sum of the previous two scores.
                    // Both previous scores must remain on the record.
                    int num2 = st.pop();       // most recent score (removed temporarily)
                    int num1 = st.peek();      // second-most-recent score (left in place, no need to pop+push it back)
                    int sum = num1 + num2;

                    st.push(num2);  // restore the score we popped
                    st.push(sum);   // add the new sum on top
                }
            }
        }

        // Sum every score currently on the record (order doesn't matter for the total).
        int result = 0;
        while (!st.isEmpty()) {
            result += st.pop();
        }
        return result;
    }
}