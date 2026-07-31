class Solution {
    // Approach: sort the array so equal elements become adjacent,
    // then track the length of each consecutive run. If any run's
    // length exceeds len/2, that value is the majority element.
    //
    // TC: O(n log n) -> dominated by Arrays.sort()
    // SC: O(log n)   -> Arrays.sort() on a primitive int[] uses
    //                   dual-pivot quicksort, in-place but with
    //                   O(log n) recursion stack space
    public int majorityElement(int[] nums) {
        int len = nums.length;

        // Edge case: single element is trivially the majority
        // (loop below can't handle this since it needs pairs).
        if (len == 1) return nums[0];

        Arrays.sort(nums);

        int count = 1; // current run length (starts at 1: first element of a run counts as 1 occurrence)
        int max = -1;  // longest run seen so far

        for (int i = 1; i < len; i++) {
            if (nums[i - 1] == nums[i]) {
                // Still in the same run -> extend it.
                count++;
                max = Math.max(count, max);

                // Early exit as soon as a run crosses majority threshold.
                if (max > len / 2) return nums[i - 1];
            } else {
                // Run broke -> start counting a new run from this element.
                count = 1;
            }
        }

        // Problem guarantees a majority always exists, so this
        // line is never actually reached (fallback only).
        return -1;
    }
}