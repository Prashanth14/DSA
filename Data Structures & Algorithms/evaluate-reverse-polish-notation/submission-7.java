/**
 * Time Complexity: O(n), where n = tokens.length
 *   - Single pass through the array.
 *   - Each token does O(1) work: a few .equals() checks, parseInt/push/pop
 *     on the stack — all constant time.
 *
 * Space Complexity: O(n)
 *   - Worst case, operands can outnumber operators before any reduction
 *     happens, so the stack can hold up to ~n/2 (still O(n)) values at once.
 */
class Solution {
    public int evalRPN(String[] tokens) {
        // Stack holds operands waiting to be combined by an operator.
        Stack<Integer> st = new Stack<>();

        for (String s : tokens) {

            // Not an operator symbol -> it's a number string, parse and push.
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
                st.push(Integer.parseInt(s));

            } else {

                if (s.equals("+")) {
                    // Addition is commutative — order of pops doesn't matter.
                    st.push(st.pop() + st.pop());

                } else if (s.equals("-")) {
                    // Subtraction is NOT commutative — order matters.
                    // num2 = top of stack (most recently pushed operand),
                    // num1 = second-from-top (pushed before num2).
                    // RPN convention: result = num1 - num2 (earlier operand minus later one).
                    int num2 = st.pop();
                    int num1 = st.pop();
                    st.push(num1 - num2);

                } else if (s.equals("*")) {
                    // Multiplication is commutative — order of pops doesn't matter.
                    st.push(st.pop() * st.pop());

                } else if (s.equals("/")) {
                    // Division is NOT commutative — same ordering rule as subtraction:
                    // result = num1 / num2 (earlier operand divided by later one).
                    int num2 = st.pop();
                    int num1 = st.pop();
                    st.push(num1 / num2);
                }
            }
        }

        // After processing all tokens, exactly one value remains — the final result.
        return st.pop();
    }
}