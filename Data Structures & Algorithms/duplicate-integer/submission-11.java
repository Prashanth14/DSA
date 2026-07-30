class Solution {
    // ---------------------------------------------------------------
    // Approach: Add every element to a HashSet. Since a Set can only
    // hold unique values, if any element was a duplicate, the set's
    // final size will be smaller than the original array length.
    //
    // Overall Time Complexity: O(n)  -> one pass through the array,
    //                                   O(1) average time per add/lookup
    // Overall Space Complexity: O(n) -> worst case, every element is
    //                                   unique and gets stored in the set
    // ---------------------------------------------------------------
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;

        // Edge case: empty array has no duplicates.
        if (len == 0) return false;

        // HashSet automatically discards duplicate values on insert.
        Set<Integer> set = new HashSet<>();

        // Add every element. Duplicate values just get "merged" —
        // set.size() will end up smaller than len if any existed.
        for (int i = 0; i < len; i++) {
            set.add(nums[i]);
        }

        // If sizes differ, some element(s) collapsed together in the
        // set -> a duplicate existed.
        if (set.size() != len) {
            return true;
        }

        // Sizes match -> every element was unique, added exactly once.
        return false;
    }
}