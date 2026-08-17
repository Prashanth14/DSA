class Solution {
    /*
     * Pattern: Stack / Nested Expression
     *
     * Key idea:
     * - `current` = string being built at the current nesting level.
     * - `num` = repeat count for the next [...] section.
     *
     * When we see '[':
     *   Save the current state (current string + repeat count)
     *   and start fresh for the nested section.
     *
     * When we see ']':
     *   The nested section is complete.
     *   Restore the previous string and repeat count,
     *   then combine:
     *
     *      previous + current repeated `repeat` times
     *
     * Example: 2[a3[b]]c
     *
     * At inner '[':
     *   previous = "a", repeat = 3
     *   current = "b"
     *
     * At inner ']':
     *   current = "a" + "b" * 3 = "abbb"
     *
     * At outer ']':
     *   current = "" + "abbb" * 2 = "abbbabbb"
     *
     * Finally append 'c' -> "abbbabbbc"
     *
     * TC: O(n + m * d) worst-case due to nested StringBuilder copying/appending,
     *     where:
     *       n = encoded input length
     *       m = decoded output length
     *       d = maximum nesting depth.
     *     Since output length is capped at 100,000, this is practical.
     *
     * SC: O(m + d)
     *     - Decoded strings can occupy O(m)
     *     - Stacks contain at most d nesting levels
     */

    public String decodeString(String s) {

        // Stores the string we were building BEFORE entering each '['.
        Stack<StringBuilder> stringStack = new Stack<>();

        // Stores the repeat count associated with each '['.
        Stack<Integer> repCountStack = new Stack<>();

        // Number currently being built. Handles multi-digit numbers:
        // 12 -> 1 -> 1 * 10 + 2 = 12
        int num = 0;

        // String being built at the CURRENT nesting level.
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                // Build multi-digit repeat count.
                num = num * 10 + (ch - '0');

            } else if (ch == '[') {

                // Entering a new nested level.
                // Save current state so we can restore it at ']'.
                repCountStack.push(num);
                stringStack.push(current);

                // Start fresh for the content inside [...].
                num = 0;
                current = new StringBuilder();

            } else if (ch == ']') {

                // Finished current nested level.
                // Restore the state from before '['.
                int repeat = repCountStack.pop();
                StringBuilder previous = stringStack.pop();

                // previous + (current repeated `repeat` times)
                for (int i = 0; i < repeat; i++) {
                    previous.append(current);
                }

                // Combined string becomes the current string
                // for the previous nesting level.
                current = previous;

            } else {

                // Normal alphabet character.
                current.append(ch);
            }
        }

        return current.toString();
    }
}