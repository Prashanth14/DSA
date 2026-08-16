class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // st holds asteroids that are still "alive" so far, in their
        // original left-to-right order. Top of stack = most recent survivor.
        Stack<Integer> st = new Stack<>();

        for (int ast : asteroids) {

            // Keep colliding while there's an actual head-on collision:
            // top of stack moving right (positive) AND current asteroid
            // moving left (negative) — they're heading toward each other.
            // If ast becomes 0 (destroyed) inside the loop, "ast < 0"
            // becomes false and the loop naturally stops.
            while (!st.isEmpty() && st.peek() > 0 && ast < 0) {

                // Trick: instead of comparing Math.abs(ast) vs st.peek()
                // separately, just add them. Since ast is negative and
                // st.peek() is positive, the SIGN of the sum tells us
                // who had the bigger magnitude (who "wins").
                int diff = ast + st.peek();

                if (diff < 0) {
                    // ast (negative) had bigger magnitude -> top loses.
                    // Pop it, and keep looping — ast might hit the next
                    // asteroid down too (chain reaction).
                    st.pop();

                } else if (diff > 0) {
                    // st.peek() (positive) had bigger magnitude -> ast loses.
                    // Mark ast as destroyed using 0 as a sentinel value
                    // (safe since real asteroid sizes are never 0).
                    // This also makes "ast < 0" false, ending the loop.
                    ast = 0;

                } else {
                    // diff == 0 -> exactly equal magnitude -> BOTH destroyed.
                    ast = 0;      // mark current asteroid as destroyed
                    st.pop();     // remove the one it collided with too
                }
            }

            // After all possible collisions: only add ast to the stack
            // if it's still "alive" (not marked destroyed via ast = 0).
            // This covers: never collided at all, OR survived by
            // destroying everything in its path.
            if (ast != 0) {
                st.add(ast);
            }
        }

        // Convert final surviving asteroids (bottom-to-top order,
        // which matches original left-to-right order) into an int[].
        int[] result = st.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }
}