class Solution {
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
             * Always check mid first.
             *
             * If target is found, immediately return its index.
             */
            if (nums[mid] == target) {

                return true;

            } else if (nums[left] < nums[mid]) {

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

            } else if(nums[left] > nums[mid]){

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
            }else if(nums[left] == nums[mid] ){
                left += 1;
            }
        }


        // Target does not exist in the array.
        return false;
    }
}