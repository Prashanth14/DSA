class Solution {
    // Approach: sort, then walk through tracking current run length.
    // Equal values (duplicates) neither extend nor reset the run.
    // A true gap resets the run. Track the best run seen so far
    // separately, updated every iteration (not just on reset).
    //
    // TC: O(n log n) -> dominated by Arrays.sort()
    // SC: O(log n)   -> Arrays.sort() on a primitive int[] uses
    //                   dual-pivot quicksort, in-place but with
    //                   O(log n) recursion stack space
    //
    // NOTE: problem explicitly requires O(n) time - this sort-based
    // approach does NOT satisfy that constraint, even though it's
    // now logically correct. A true O(n) solution needs a HashSet-based
    // approach instead (no sorting).
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        Arrays.sort(nums);

        int i = 0;
        int counter = 1;        // length of the current consecutive run
        int longestCounter = 1; // best run length seen so far

        while (i < len - 1) {
            if (nums[i] + 1 == nums[i + 1]) {
                counter++; // consecutive value -> extend current run
            } else if (nums[i + 1] > nums[i] + 1) {
                counter = 1; // real gap -> reset (duplicates fall through, no-op)
            }
            longestCounter = Math.max(counter, longestCounter); // update every iteration
            i++;
        }

        return longestCounter;
    }
}