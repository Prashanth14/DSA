/**
 *
 * Approach: Two Pointers with running wall maximums  (O(1) space)
 *
 * ---------------------------------------------------------------------
 * BASE FORMULA - think COLUMN BY COLUMN, not "basin by basin"
 * ---------------------------------------------------------------------
 * For each index i, the water sitting on top of bar i is held in by the
 * tallest wall to its left and the tallest wall to its right. The level
 * is set by the SHORTER of the two (water spills over the lower side),
 * minus the bar itself:
 *
 *      water[i] = min(leftMax[i], rightMax[i]) - height[i]
 *
 * The naive way is to precompute both arrays -> O(n) time, O(n) space.
 * This version gets the same answer in O(1) space.
 *
 * ---------------------------------------------------------------------
 * KEY INSIGHT - you never need the exact taller wall
 * ---------------------------------------------------------------------
 * We only carry two scalars, each exact for the side it has scanned:
 *      leftMax  = true max of height[0..left]
 *      rightMax = true max of height[right..len-1]
 *
 * When leftMax < rightMax, look at the column at `left`:
 *   - its true right wall is AT LEAST rightMax (walls only grow as we
 *     look further right), and rightMax > leftMax,
 *   - therefore min(trueLeftMax, trueRightMax) == leftMax.
 *
 * So leftMax alone settles that column, even though the real right wall
 * is still unknown. Symmetrically for the other branch.
 *
 *   >>> Rule: always advance the pointer on the side whose running max
 *   >>> is SMALLER, because that side's value is already final.
 *
 * ---------------------------------------------------------------------
 * WORKED EXAMPLE  height = [0,2,0,3,1,0,1,3,2,1]  ->  9
 * ---------------------------------------------------------------------
 *  l  r  leftMax rightMax  branch  added  res
 *  0  9     0       1       left     0      0    (l->1, leftMax=2)
 *  1  8     2       1       right    0      0    (r->8, rightMax=2)
 *  1  7     2       2       right    0      0    (r->7, rightMax=3)
 *  2  7     2       3       left     2      2    (bar 0, wall 2)
 *  3  7     3       3       left     0      2
 *  3  6     3       3       right    2      4
 *  3  5     3       3       right    3      7    (bar 0, wall 3)
 *  3  4     3       3       right    2      9
 *  3  3     -       -       loop ends (left == right)
 *
 * ---------------------------------------------------------------------
 * COMPLEXITY   (n = height.length)
 * ---------------------------------------------------------------------
 * Time  : O(n) - single pass; left and right together cover every index
 *                once, each iteration doing O(1) work.
 *                Optimal - every bar must be inspected at least once.
 * Space : O(1) - four scalars, no auxiliary arrays. This is the space
 *                optimal solution; the prefix/suffix-array version is
 *                also O(n) time but costs O(n) memory.
 */
class Solution {
    public int trap(int[] height) {
        int len = height.length;

        // Pointers start at the outermost bars and close inward.
        int left = 0, right = len - 1;

        // Seed each running max with its own edge bar. The outermost bars
        // can never hold water (nothing beyond them to dam it), so they
        // serve only as the initial walls.
        int leftMax = height[left];
        int rightMax = height[right];

        int res = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                // Left wall is the shorter one -> it is the binding
                // constraint, so this column can be settled now.

                // Move FIRST, then update. Refreshing leftMax with the new
                // bar before subtracting guarantees leftMax >= height[left],
                // so the contribution is never negative - no clamp needed.
                left++;
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];

            } else {
                // Right wall is shorter (or equal - tie goes here, which is
                // fine since either side is then valid). Mirror logic.
                right--;
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
            }
        }

        return res;
    }
}