class Solution {

    /*
     * Pattern: Monotonic Increasing Stack
     *
     * Key Idea:
     * For every bar heights[i], treat heights[i] as the rectangle height.
     *
     * We need to know how far this rectangle can extend:
     *
     *   LEFT  -> nearest bar strictly smaller than heights[i]
     *   RIGHT -> nearest bar strictly smaller than heights[i]
     *
     * Everything between those two smaller bars has height >= heights[i],
     * so heights[i] can form a rectangle across that entire range.
     *
     * Formula:
     *
     *      width = right[i] - left[i] - 1
     *      area  = heights[i] * width
     *
     *
     * Example:
     *
     * heights = [2, 1, 5, 6, 2, 3]
     *                  ↑
     *               i = 2
     *             height = 5
     *
     * nearest smaller LEFT:
     * index 1 -> height 1
     *
     * nearest smaller RIGHT:
     * index 4 -> height 2
     *
     * left[2]  = 1
     * right[2] = 4
     *
     * Valid rectangle:
     * indices 2...3 -> [5, 6]
     *
     * width = 4 - 1 - 1 = 2
     * area  = 5 * 2 = 10
     *
     *
     * Why use >= while popping?
     *
     * We are looking for a STRICTLY SMALLER bar.
     * Therefore, equal-height bars cannot be our boundary
     * and must also be popped.
     *
     *
     * Boundary values:
     *
     * If there is NO smaller bar on the LEFT:
     *      left[i] = -1
     *
     * If there is NO smaller bar on the RIGHT:
     *      right[i] = len
     *
     * These imaginary boundaries make the width formula work naturally.
     *
     *
     * Time Complexity: O(n)
     *
     * We make three O(n) passes:
     *   1. Find nearest smaller on right
     *   2. Find nearest smaller on left
     *   3. Calculate areas
     *
     * Even though there is a while loop inside each pass,
     * every index is pushed once and popped at most once.
     *
     * Therefore stack operations across an entire pass = O(n).
     *
     * TC = O(n)
     *
     *
     * Space Complexity: O(n)
     *
     * left[]  -> O(n)
     * right[] -> O(n)
     * stack   -> O(n)
     *
     * SC = O(n)
     */

    public int largestRectangleArea(int[] heights) {

        int len = heights.length;
        int maxArea = 0;

        // left[i] = index of nearest STRICTLY smaller bar on the left.
        int[] left = new int[len];

        // right[i] = index of nearest STRICTLY smaller bar on the right.
        int[] right = new int[len];

        /*
         * Stack stores INDICES, not heights.
         *
         * We need indices because eventually we need the distance
         * between the left and right boundaries to calculate width.
         */
        Stack<Integer> st = new Stack<>();


        /*
         * PASS 1: Find nearest smaller element on the RIGHT.
         *
         * Scan right -> left.
         */
        for (int x = len - 1; x >= 0; x--) {

            /*
             * Remove bars that are >= current height.
             *
             * They cannot be the nearest strictly smaller
             * bar for heights[x].
             */
            while (!st.isEmpty() &&
                   heights[st.peek()] >= heights[x]) {
                st.pop();
            }

            /*
             * If stack is empty:
             * there is no smaller bar on the right.
             *
             * Use len as an imaginary right boundary.
             *
             * Otherwise, stack top is the nearest smaller bar.
             */
            right[x] = st.isEmpty() ? len : st.peek();

            // Current index may become the right boundary for earlier bars.
            st.push(x);
        }


        // Reuse the same stack for the left-boundary pass.
        st.clear();


        /*
         * PASS 2: Find nearest smaller element on the LEFT.
         *
         * Scan left -> right.
         */
        for (int y = 0; y < len; y++) {

            /*
             * Remove bars that are >= current height.
             *
             * After this loop, the stack top (if present)
             * is the nearest strictly smaller bar on the left.
             */
            while (!st.isEmpty() &&
                   heights[st.peek()] >= heights[y]) {
                st.pop();
            }

            /*
             * If stack is empty:
             * there is no smaller bar on the left.
             *
             * Use -1 as an imaginary left boundary.
             */
            left[y] = st.isEmpty() ? -1 : st.peek();

            // Current index may become the left boundary for future bars.
            st.push(y);
        }


        /*
         * PASS 3: Calculate the maximum rectangle
         * using every bar as the rectangle height.
         */
        for (int i = 0; i < len; i++) {

            /*
             * left[i] and right[i] are OUTSIDE the rectangle.
             *
             * Valid indices:
             *
             *      left[i] + 1 ... right[i] - 1
             *
             * Therefore:
             *
             *      width = right[i] - left[i] - 1
             */
            int width = right[i] - left[i] - 1;

            int currentArea = heights[i] * width;

            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }
}