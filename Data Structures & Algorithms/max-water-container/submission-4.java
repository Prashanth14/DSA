/**
 *
 * Approach: Two Pointers (greedy shrink from the widest container)
 *
 * Area between two bars i and j is:
 *      area = min(height[i], height[j]) * (j - i)
 * It is bounded by the SHORTER wall, since water spills over it.
 *
 * Start with the widest possible container (left = 0, right = len-1) and
 * shrink inward. At each step we move the pointer at the SHORTER wall.
 *
 * Why moving the shorter wall is safe (the key insight):
 *   Suppose height[left] < height[right]. Any other container using
 *   `left` must have a smaller width (right can only move inward) and a
 *   height still capped at height[left] or less. So every remaining pair
 *   involving `left` is <= the area we just computed - `left` can never
 *   be part of a better answer and is discarded with no loss.
 *   Moving the taller wall instead would shrink the width while the
 *   height stays capped by the shorter wall, so it could only lose area
 *   and might skip the true optimum.
 *
 * Each iteration discards exactly one bar, so the pointers meet after
 * n-1 steps and every candidate that matters has been considered.
 *
 * Time Complexity:  O(n) - left and right together traverse the array
 *                   once; each iteration does O(1) work.
 * Space Complexity: O(1) - only a few scalar variables, no extra structures.
 */
class Solution {
    public int maxArea(int[] heights) {
        int maxWater = 0;
        int len = heights.length;

        // Widest container first: maximum width, then trade width for height.
        int left = 0;
        int right = len - 1;

        while (left < right) {
            // Water level is capped by the shorter of the two walls.
            int height = Math.min(heights[left], heights[right]);
            int width = right - left;
            maxWater = Math.max(maxWater, height * width);

            // Move the shorter wall inward - it is the limiting factor, and
            // keeping it can never yield a larger area (see note above).
            // On a tie, either side may be moved; here we move `right`.
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}