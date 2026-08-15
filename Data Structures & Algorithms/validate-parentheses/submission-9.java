class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        if( s == null) return false;

        int len = s.length();

        // Fast rejection: a valid string must have even length,
        // since every matched pair contributes exactly 2 characters.
        // This is a cheap O(1) check that skips unnecessary scanning.
        if (len % 2 != 0) return false;

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                // Opening bracket -> push it; we expect to see its
                // matching closer later, in reverse order (LIFO).
                st.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                // Closing bracket found, but nothing open to match it
                // against -> automatically invalid (e.g. ")(" case).
                if (st.isEmpty()) {
                    return false;
                }

                // Check what's currently open (top of stack) against
                // this closing bracket to see if the types actually pair up.
                char top = st.peek();
                if ((top == '(' && ch == ')') ||
                    (top == '{' && ch == '}') ||
                    (top == '[' && ch == ']')) {
                    // Correct match -> this pair is resolved, remove it.
                    st.pop();
                } else {
                    // Wrong bracket type on top -> order/type mismatch
                    // (e.g. "[(])" -> ']' doesn't match '(' on top).
                    return false;
                }
            }
            // Any other character is ignored (not expected per constraints).
        }

        // If every opener found its matching closer, the stack should
        // be empty by the end. Anything left means unclosed brackets.
        
        return st.isEmpty();
    }
}