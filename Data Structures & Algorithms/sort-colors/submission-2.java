class Solution {
    // Approach: brute force sort — compare every pair (i, j) where
    // j > i, and swap if out of order. Effectively a basic sort,
    // ignoring the fact that only 3 distinct values (0,1,2) exist.
    // TC: O(n^2) -> nested loop, every pair checked
    // SC: O(1) -> in-place swaps, only a temp variable
    public void sortColors(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    // swap so smaller value moves earlier
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }
}