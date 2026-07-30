class Solution {
    // ---------------------------------------------------------------
    // Approach: Sort the array, then check neighboring elements.
    // If any value repeats, sorting brings the duplicates right next
    // to each other — so a duplicate always shows up as two equal
    // ADJACENT elements after sorting.
    //
    // Overall Time Complexity: O(n log n) -> dominated by Arrays.sort()
    // Overall Space Complexity: O(log n)  -> Arrays.sort() on a primitive
    //                                        int[] uses dual-pivot quicksort,
    //                                        which sorts in-place but still
    //                                        uses O(log n) recursion stack
    //                                        space internally (not truly O(1))
    // ---------------------------------------------------------------
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;

        // Edge case: empty array can't have duplicates.
        // Also avoids doing unnecessary work below.
        if (len == 0) return false;

        // Sort so that any duplicate values become adjacent to each other.
        // O(n log n) time.
        Arrays.sort(nums);

        // Single pass: check each element against its immediate predecessor.
        // If sorting worked as expected, duplicates will always be next
        // to each other, so we only need to compare neighbors — not
        // every pair like the brute force version.
        // O(n) time.
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }

        // No adjacent match found after sorting -> all elements unique.
        return false;
    }
}