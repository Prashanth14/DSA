class Solution {

    /*
     * Pattern: Binary Search on Rotated Sorted Array
     *
     * Goal:
     * Find the index of "target" in a rotated sorted array.
     * Return -1 if target does not exist.
     *
     * All elements are UNIQUE.
     *
     *
     * ---------------------------------------------------------
     * KEY OBSERVATION
     * ---------------------------------------------------------
     *
     * A rotated sorted array can be thought of as two
     * increasing sorted portions.
     *
     * Example:
     *
     * Original:
     * [1,2,3,4,5,6]
     *
     * Rotated:
     * [3,4,5,6,1,2]
     *
     *          [3,4,5,6] [1,2]
     *           sorted    sorted
     *
     * Even though the entire array is not sorted normally,
     * for any mid position, AT LEAST ONE HALF must be sorted.
     *
     * Therefore, at every binary-search step:
     *
     * 1. Check if nums[mid] == target.
     * 2. Determine which half is sorted.
     * 3. Check whether target lies inside that sorted half.
     * 4. Search that half if target belongs there.
     *    Otherwise search the other half.
     *
     *
     * ---------------------------------------------------------
     * HOW TO DETERMINE WHICH HALF IS SORTED?
     * ---------------------------------------------------------
     *
     * If:
     *
     *      nums[left] <= nums[mid]
     *
     * then LEFT HALF is sorted.
     *
     * Otherwise:
     *
     *      RIGHT HALF is sorted.
     *
     *
     * ---------------------------------------------------------
     * CASE 1: LEFT HALF IS SORTED
     * ---------------------------------------------------------
     *
     * Condition:
     *
     *      nums[left] <= nums[mid]
     *
     * Example:
     *
     * [3,4,5,6,1,2]
     *  L   M     R
     *
     * Left side:
     *
     * [3,4,5]
     *
     * is sorted.
     *
     * Now check whether target lies inside:
     *
     *      nums[left] <= target < nums[mid]
     *
     * If YES:
     *
     *      target must be in the left half
     *
     *      right = mid - 1
     *
     * Otherwise:
     *
     *      target must be searched on the right
     *
     *      left = mid + 1
     *
     *
     * ---------------------------------------------------------
     * CASE 2: RIGHT HALF IS SORTED
     * ---------------------------------------------------------
     *
     * If the left half is NOT sorted, then the right
     * half must be sorted because all elements are unique.
     *
     * Example:
     *
     * [5,6,1,2,3,4]
     *  L   M     R
     *
     * Right side:
     *
     * [1,2,3,4]
     *
     * is sorted.
     *
     * Check whether target lies inside:
     *
     *      nums[mid] < target <= nums[right]
     *
     * If YES:
     *
     *      target must be in the right half
     *
     *      left = mid + 1
     *
     * Otherwise:
     *
     *      search the left half
     *
     *      right = mid - 1
     *
     *
     * ---------------------------------------------------------
     * IMPORTANT BOUNDARIES
     * ---------------------------------------------------------
     *
     * Left sorted range:
     *
     *      nums[left] <= target < nums[mid]
     *
     *                      ^
     *                 NOT <= nums[mid]
     *
     * because nums[mid] == target was already checked.
     *
     *
     * Right sorted range:
     *
     *      nums[mid] < target <= nums[right]
     *
     *          ^
     *     NOT <= target
     *
     * because nums[mid] == target was already checked.
     *
     *
     * ---------------------------------------------------------
     * WHY while(left <= right)?
     * ---------------------------------------------------------
     *
     * We are searching for an EXACT target.
     *
     * When:
     *
     *      left == right
     *
     * there is still one element that must be checked.
     *
     * Therefore:
     *
     *      while(left <= right)
     *
     * If target is never found, eventually:
     *
     *      left > right
     *
     * and we return -1.
     *
     *
     * ---------------------------------------------------------
     * EASY WAY TO REMEMBER
     * ---------------------------------------------------------
     *
     *              MID
     *               |
     *        Which half is sorted?
     *              /   \
     *           LEFT   RIGHT
     *             |      |
     *         Is target inside
     *         the sorted half?
     *           /       \
     *         YES       NO
     *          |         |
     *      search it   search other half
     *
     *
     * ---------------------------------------------------------
     * TIME & SPACE COMPLEXITY
     * ---------------------------------------------------------
     *
     * Every iteration eliminates approximately half
     * of the remaining search space.
     *
     * TC: O(log n)
     *
     * We only use left, right and mid variables.
     *
     * SC: O(1)
     */

    public int search(int[] nums, int target) {

        int len = nums.length;

        // Binary-search boundaries.
        int left = 0;
        int right = len - 1;


        /*
         * Use <= because when left == right,
         * there is still one element left to check.
         */
        while (left <= right) {

            // Overflow-safe middle index.
            int mid = left + (right - left) / 2;


            /*
             * Always check mid first.
             *
             * If target is found, immediately return its index.
             */
            if (nums[mid] == target) {

                return mid;

            } else if (nums[left] <= nums[mid]) {

                /*
                 * CASE 1:
                 * LEFT HALF IS SORTED.
                 *
                 * Sorted range:
                 *
                 * nums[left] .... nums[mid]
                 */

                if (target >= nums[left] && target < nums[mid]) {

                    /*
                     * Target lies inside the sorted left half.
                     *
                     * Example:
                     *
                     * [3,4,5,6,1,2]
                     *  L   M
                     *
                     * target = 4
                     *
                     * 3 <= 4 < 5
                     *
                     * Therefore search left.
                     */
                    right = mid - 1;

                } else {

                    /*
                     * Target is NOT inside the sorted left half.
                     *
                     * Therefore search the right half.
                     */
                    left = mid + 1;
                }

            } else {

                /*
                 * CASE 2:
                 * RIGHT HALF IS SORTED.
                 *
                 * Since the left half was not sorted,
                 * the right half must be sorted.
                 *
                 * Sorted range:
                 *
                 * nums[mid] .... nums[right]
                 */

                if (target > nums[mid] && target <= nums[right]) {

                    /*
                     * Target lies inside the sorted right half.
                     *
                     * Therefore search right.
                     */
                    left = mid + 1;

                } else {

                    /*
                     * Target is NOT inside the sorted right half.
                     *
                     * Therefore search left.
                     */
                    right = mid - 1;
                }
            }
        }


        // Target does not exist in the array.
        return -1;
    }
}