class Solution {

    /*
     * Problem: Search in Rotated Sorted Array II
     *
     * Pattern:
     * Binary Search on a Rotated Sorted Array WITH DUPLICATES
     *
     * Main difference from Rotated Sorted Array I:
     *
     * Array I:
     *      All values are UNIQUE.
     *
     * Array II:
     *      DUPLICATES are allowed.
     *
     * Because of duplicates, sometimes we cannot determine
     * whether the left half or right half is sorted.
     *
     *
     * -------------------------------------------------------
     * MAIN IDEA
     * -------------------------------------------------------
     *
     * At every iteration:
     *
     * 1. Check nums[mid] == target.
     *
     * 2. If nums[left] < nums[mid]:
     *      LEFT half is strictly sorted.
     *
     * 3. If nums[left] > nums[mid]:
     *      RIGHT half is sorted.
     *
     * 4. If nums[left] == nums[mid]:
     *      duplicates create ambiguity.
     *      We cannot determine the sorted half using this
     *      comparison, so safely remove nums[left]:
     *
     *          left++
     *
     *
     * -------------------------------------------------------
     * WHY DUPLICATES CREATE A PROBLEM
     * -------------------------------------------------------
     *
     * Example:
     *
     * nums = [1,1,1,3,1]
     *
     *         L   M   R
     *
     * nums[left] = 1
     * nums[mid]  = 1
     *
     * nums[left] == nums[mid]
     *
     * From this comparison alone, we cannot determine
     * which side contains the rotation.
     *
     * Since nums[mid] was already checked against target,
     * when nums[left] == nums[mid], removing nums[left]
     * does not lose a unique possible target value.
     *
     * Therefore:
     *
     *      left++
     *
     *
     * -------------------------------------------------------
     * CASE 1: LEFT HALF IS SORTED
     * -------------------------------------------------------
     *
     * Condition:
     *
     *      nums[left] < nums[mid]
     *
     * Example:
     *
     * [3,4,5,6,1,2]
     *  L   M
     *
     * Left portion is sorted.
     *
     * Check whether target lies inside:
     *
     *      nums[left] <= target < nums[mid]
     *
     * If YES:
     *      right = mid - 1
     *
     * Otherwise:
     *      left = mid + 1
     *
     *
     * -------------------------------------------------------
     * CASE 2: RIGHT HALF IS SORTED
     * -------------------------------------------------------
     *
     * Condition:
     *
     *      nums[left] > nums[mid]
     *
     * Example:
     *
     * [5,6,1,2,3,4]
     *  L   M     R
     *
     * Right portion is sorted.
     *
     * Check whether target lies inside:
     *
     *      nums[mid] < target <= nums[right]
     *
     * If YES:
     *      left = mid + 1
     *
     * Otherwise:
     *      right = mid - 1
     *
     *
     * -------------------------------------------------------
     * COMPLEXITY
     * -------------------------------------------------------
     *
     * Without duplicate ambiguity, binary search removes
     * approximately half of the search space:
     *
     *      O(log n)
     *
     * BUT duplicates can force us to do:
     *
     *      left++
     *
     * repeatedly.
     *
     * Example:
     *
     * [1,1,1,1,1,1,1,1,1]
     *
     * In the worst case, we may inspect almost every element.
     *
     * Therefore:
     *
     * Average/typical binary-search behavior: O(log n)
     * Worst-case TC: O(n)
     *
     * SC: O(1)
     */

    public boolean search(int[] nums, int target) {

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
             * STEP 1:
             * Always check the middle element first.
             */
            if (nums[mid] == target) {
                return true;
            }

            /*
             * CASE 1:
             * nums[left] < nums[mid]
             *
             * LEFT half is strictly sorted.
             */
            else if (nums[left] < nums[mid]) {

                /*
                 * Check whether target belongs inside
                 * the sorted left range:
                 *
                 * nums[left] <= target < nums[mid]
                 */
                if (target >= nums[left] && target < nums[mid]) {

                    // Target must be on the left.
                    right = mid - 1;

                } else {

                    // Target is not in the sorted left half.
                    // Search the right half.
                    left = mid + 1;
                }
            }

            /*
             * CASE 2:
             * nums[left] > nums[mid]
             *
             * Rotation lies between left and mid,
             * so the RIGHT half is sorted.
             */
            else if (nums[left] > nums[mid]) {

                /*
                 * Check whether target belongs inside
                 * the sorted right range:
                 *
                 * nums[mid] < target <= nums[right]
                 */
                if (target > nums[mid] && target <= nums[right]) {

                    // Target must be on the right.
                    left = mid + 1;

                } else {

                    // Target is not in the sorted right half.
                    // Search the left half.
                    right = mid - 1;
                }
            }

            /*
             * CASE 3: DUPLICATE AMBIGUITY
             *
             * nums[left] == nums[mid]
             *
             * Example:
             *
             * [1,1,1,3,1]
             *  L   M
             *
             * We cannot determine the sorted half from
             * nums[left] vs nums[mid].
             *
             * Since nums[mid] != target was already checked,
             * and nums[left] == nums[mid], nums[left] is also
             * not the target.
             *
             * Therefore safely discard nums[left].
             */
            else {
                left++;
            }
        }

        // Search space exhausted: target does not exist.
        return false;
    }
}