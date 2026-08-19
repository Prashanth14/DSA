class Solution {

    /*
     * Pattern: Binary Search on Rotated Sorted Array
     *
     * Goal:
     * Find the MINIMUM element in a sorted array that has been rotated.
     *
     * Example:
     *
     * Original:
     * [1,2,3,4,5,6]
     *
     * Rotated:
     * [3,4,5,6,1,2]
     *
     * We can think of it as TWO increasing sorted portions:
     *
     * [3,4,5,6] [1,2]
     *             ^
     *           minimum
     *
     * The minimum is the point where the rotation happened.
     *
     *
     * ---------------------------------------------------------
     * KEY IDEA: Compare nums[mid] with nums[right]
     * ---------------------------------------------------------
     *
     * Because all elements are UNIQUE, there are two cases.
     *
     *
     * CASE 1:
     *
     * nums[mid] > nums[right]
     *
     * Example:
     *
     * [3,4,5,6,1,2]
     *  L   M     R
     *
     * nums[mid]   = 5
     * nums[right] = 2
     *
     * 5 > 2
     *
     * This means mid is in the LEFT/LARGER sorted portion.
     *
     * [3,4,5,6] [1,2]
     *      ^       ^
     *     mid     min
     *
     * Therefore the minimum MUST be strictly to the
     * RIGHT of mid.
     *
     * mid itself cannot be the minimum, so:
     *
     *      left = mid + 1
     *
     *
     * ---------------------------------------------------------
     * CASE 2:
     *
     * nums[mid] < nums[right]
     *
     * Example:
     *
     * [4,5,0,1,2,3]
     *  L   M     R
     *
     * nums[mid]   = 0
     * nums[right] = 3
     *
     * 0 < 3
     *
     * This means the portion from mid to right is sorted.
     *
     * Therefore the minimum is either:
     *
     *      - nums[mid] itself
     *      OR
     *      - somewhere to the LEFT of mid
     *
     * IMPORTANT:
     * mid could itself be the minimum, so we CANNOT discard it.
     *
     * Therefore:
     *
     *      right = mid
     *
     *
     * ---------------------------------------------------------
     * EASY WAY TO REMEMBER
     * ---------------------------------------------------------
     *
     * Compare MID with RIGHT:
     *
     * nums[mid] > nums[right]
     *
     *      minimum is RIGHT of mid
     *      mid cannot be answer
     *
     *      left = mid + 1
     *
     *
     * nums[mid] < nums[right]
     *
     *      minimum is at mid OR LEFT of mid
     *      mid could be answer
     *
     *      right = mid
     *
     *
     * ---------------------------------------------------------
     * ALREADY SORTED ARRAY
     * ---------------------------------------------------------
     *
     * nums = [4,5,6,7]
     *
     * Binary search keeps moving right toward mid:
     *
     * nums[mid] < nums[right]
     *
     *      right = mid
     *
     * Eventually:
     *
     *      left = right = 0
     *
     * So nums[0] = 4 is correctly returned.
     *
     *
     * ---------------------------------------------------------
     * WHY while(left < right)?
     * ---------------------------------------------------------
     *
     * We keep shrinking the search space while there is
     * more than one possible candidate.
     *
     * Eventually:
     *
     *      left == right
     *
     * Both pointers land on the minimum element.
     *
     * Therefore:
     *
     *      return nums[left]
     *
     *
     * ---------------------------------------------------------
     * COMPLEXITY
     * ---------------------------------------------------------
     *
     * Every iteration eliminates roughly half of the
     * remaining search space.
     *
     * TC: O(log n)
     *
     * We only use a few integer variables.
     *
     * SC: O(1)
     */

    public int findMin(int[] nums) {

        int len = nums.length;

        // Search space contains indices where the minimum may exist.
        int left = 0;
        int right = len - 1;

        /*
         * Continue until only ONE possible index remains.
         */
        while (left < right) {

            // Overflow-safe middle index.
            int mid = left + (right - left) / 2;

            /*
             * CASE 1:
             *
             * mid value > right value
             *
             * Example:
             * [3,4,5,6,1,2]
             *      M     R
             *
             * mid is in the larger/left sorted portion.
             *
             * Therefore minimum must be strictly RIGHT of mid.
             *
             * We can safely discard mid.
             */
            if (nums[mid] > nums[right]) {

                left = mid + 1;

            } else {

                /*
                 * CASE 2:
                 *
                 * mid value < right value
                 *
                 * Example:
                 * [4,5,0,1,2,3]
                 *      M     R
                 *
                 * The minimum is at mid or somewhere
                 * to the LEFT of mid.
                 *
                 * Keep mid because mid itself could be
                 * the minimum.
                 */
                right = mid;
            }
        }

        /*
         * Binary search finishes when:
         *
         *      left == right
         *
         * That index contains the minimum element.
         */
        return nums[left];
    }
}