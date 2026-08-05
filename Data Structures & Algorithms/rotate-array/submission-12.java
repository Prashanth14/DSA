/**
 * 
 *
 * Approach: Triple Reversal
 *
 * A right rotation by k means the last k elements move to the front.
 * Split the array into two blocks:
 *      A = nums[0 .. len-k-1]   (first len-k elements)
 *      B = nums[len-k .. len-1] (last k elements)
 * We want to transform  A | B  into  B | A.
 *
 * Using the identity (XY)^r = Y^r X^r, we reverse each block in place,
 * then reverse the whole array:
 *      A | B  ->  A^r | B^r  ->  reverse all  ->  B | A
 *
 * Example: nums = [1,2,3,4,5,6,7], k = 3  (len-k = 4)
 *      reverse(0, 3)  ->  [4,3,2,1, 5,6,7]
 *      reverse(4, 6)  ->  [4,3,2,1, 7,6,5]
 *      reverse(0, 6)  ->  [5,6,7, 1,2,3,4]   <- rotated right by 3
 *
 * Time Complexity:  O(n) - each element is swapped a constant number of
 *                   times across the three reversals (~2n writes total).
 * Space Complexity: O(1) - all swaps are done in place, only a temp
 *                   variable is used.
 */
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;

        // k can exceed len; rotating by len is a no-op, so only the
        // remainder matters. Also keeps len-k a valid index.
        k = k % len;

        reverse(nums, 0, len - k - 1);   // reverse the first len-k elements  -> A^r
        reverse(nums, len - k, len - 1); // reverse the last k elements       -> B^r
        reverse(nums, 0, len - 1);       // reverse everything                -> B | A
    }

    /**
     * Reverses nums[i..j] in place using two pointers converging
     * from both ends. O(j - i) time, O(1) space.
     */
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}