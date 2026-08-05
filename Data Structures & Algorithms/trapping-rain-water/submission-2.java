/**
 *
 * Approach: Prefix / Suffix Maximum Arrays (precomputed walls)
 *
 * ---------------------------------------------------------------------
 * CORE INSIGHT - think COLUMN BY COLUMN, not "basin by basin"
 * ---------------------------------------------------------------------
 * Do not try to find puddles. Instead ask, for each index i independently:
 *
 *      "How deep is the water sitting directly on top of bar i?"
 *
 * Water at i is held in by the tallest wall to its LEFT and the tallest
 * wall to its RIGHT. The water level is set by the SHORTER of those two
 * walls - water spills over the lower side. Then subtract the bar itself,
 * since the bar occupies that space:
 *
 *      water[i] = min(leftMax[i], rightMax[i]) - height[i]
 *
 * Sum over all i and you have the answer. Each column is solved in
 * isolation, which is what makes this so much simpler than it looks.
 *
 * Note leftMax[i] and rightMax[i] here are INCLUSIVE of height[i], so
 * both are always >= height[i] and water[i] is never negative - no
 * Math.max(0, ...) clamp is needed.
 *
 * ---------------------------------------------------------------------
 * WORKED EXAMPLE  height = [0,2,0,3,1,0,1,3,2,1]
 * ---------------------------------------------------------------------
 *   index      0  1  2  3  4  5  6  7  8  9
 *   height     0  2  0  3  1  0  1  3  2  1
 *   leftMax    0  2  2  3  3  3  3  3  3  3
 *   rightMax   3  3  3  3  3  3  3  3  2  1
 *   min        0  2  2  3  3  3  3  3  2  1
 *   - height   0  0  2  0  2  3  2  0  0  0   -> total = 9
 *
 * ---------------------------------------------------------------------
 * COMPLEXITY   (n = height.length)
 * ---------------------------------------------------------------------
 * Time  : O(n) - three independent single passes (left, right, sum).
 *                Optimal: every bar must be examined at least once.
 * Space : O(n) - two auxiliary arrays of size n.
 *                See the note at the bottom for an O(1) space variant.
 */
class Solution {
    public int trap(int[] height) {
        int len = height.length;

        // leftMax[i]  = tallest bar in height[0..i]   (inclusive)
        // rightMax[i] = tallest bar in height[i..n-1] (inclusive)
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        // ---- Pass 1: sweep LEFT -> RIGHT, carrying the running max ----
        // Base case: nothing to the left of index 0, so the wall is itself.
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // ---- Pass 2: sweep RIGHT -> LEFT, same idea mirrored ----
        // Base case: nothing to the right of the last index.
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // ---- Pass 3: accumulate the water standing on each bar ----
        // The shorter wall caps the water level; the bar displaces the rest.
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}