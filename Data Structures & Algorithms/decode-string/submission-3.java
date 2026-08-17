/**
 * Pattern: Stack (single-stack, character-by-character encode/decode)
 *
 * Idea:
 *   Push every character onto the stack as we scan, EXCEPT when we hit ']'.
 *   On ']', the stack top holds (from top to bottom): the inner decoded
 *   substring chars, then '[', then the digits of its repeat count.
 *   Pop the substring, reverse it back to correct order, discard '[',
 *   pop the digits to rebuild the number, repeat the substring that many
 *   times, and push the expanded result back onto the stack char-by-char
 *   (so an outer bracket can later scoop it up the same way).
 *   At the very end the stack holds the fully decoded answer in reverse
 *   order, so we pop everything out and reverse once more.
 *
 * Example: "3[a2[c]]"
 *   -> inner "2[c]" decodes to "cc"
 *   -> outer "3[acc]" decodes to "accaccacc"
 *
 * Time Complexity:  O(n * maxK) in the worst case, where n = s.length()
 *                    and maxK = largest repeat multiplier present in s.
 *                    Each time a bracket closes, its decoded substring is
 *                    copied (repeated) and re-pushed onto the stack, so a
 *                    character nested inside several brackets can be
 *                    copied once per enclosing bracket it's part of.
 *
 * Space Complexity: O(n * maxK) as well — the stack (and the StringBuilders
 *                    used while expanding a bracket) can hold up to the
 *                    size of the largest fully-expanded substring at once.
 */
class Solution {
    public String decodeString(String s) {
        int len = s.length();
 
        // Single stack holds: plain letters, digit chars, and '[' markers,
        // all in the order they were scanned/pushed.
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();
 
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
 
            // Scratch buffer used only while resolving one ']' — holds the
            // decoded (pre-repeat) substring for the bracket just closed.
            StringBuilder sb = new StringBuilder();
 
            if (ch != ']') {
                // Letters, digits, and '[' are simply buffered on the stack
                // until a matching ']' tells us it's time to decode them.
                st.push(ch);
            } else if (ch == ']') {
 
                // --- Step 1: pop everything back to the matching '[' ---
                // These are the characters that were INSIDE the brackets,
                // popped in reverse (last-pushed-first) order.
                while (st.peek() != '[') {
                    sb.append(st.pop());
                }
                // Undo the reversal caused by popping, so sb now reads
                // left-to-right exactly as it appeared inside the brackets.
                sb.reverse();
 
                // Discard the now-consumed '[' marker.
                st.pop();
 
                // --- Step 2: pop the digits directly below '[' to rebuild
                //     the repeat count ---
                // Digits were pushed in normal left-to-right order (e.g. "12"
                // pushed as '1' then '2'), so the stack top is the LEAST
                // significant digit. Popping while multiplying by an
                // increasing power of 10 (place) reconstructs the correct
                // integer without needing to reverse the digits.
                int num = 0;
                int place = 1;
                while (!st.isEmpty() && Character.isDigit(st.peek())) {
                    num = num + (st.pop() - '0') * place;
                    place = place * 10;
                }
 
                // --- Step 3: build the expanded (repeated) substring ---
                String decoded = sb.toString();
                StringBuilder repeated = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    repeated.append(decoded);
                }
 
                // --- Step 4: push the expansion back onto the stack ---
                // Pushed char-by-char (not as one block) so that if this
                // bracket is itself nested inside an outer bracket, the
                // outer ']' can pop these characters the exact same way.
                for (int k = 0; k < repeated.length(); k++) {
                    st.push(repeated.charAt(k));
                }
            }
        }
 
        // All brackets are resolved — whatever remains on the stack is the
        // final answer, but in reverse (last-pushed-first) order.
        while (!st.isEmpty()) {
            res.append(st.pop());
        }
 
        // Reverse once more to restore the correct left-to-right order.
        return res.reverse().toString();
    }
}