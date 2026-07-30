class Solution {
    // ---------------------------------------------------------------
    // Approach: Single pass — for each element in nums, place it in
    // both its original position and its "shifted" position (i + len)
    // in the answer array. This builds the concatenated array directly,
    // without ever creating two separate arrays and merging them.
    //
    // Overall Time Complexity: O(n)  -> one pass over nums, two writes
    //                                   per element
    // Overall Space Complexity: O(n) -> the output array ans (size 2n),
    //                                   required by the problem itself,
    //                                   not extra/avoidable space
    // ---------------------------------------------------------------
    public int[] getConcatenation(int[] nums) {
        int len = nums.length;

        // Output array is double the size of nums, as required.
        int[] ans = new int[2 * len];

        for (int i = 0; i < len; i++) {
            // First half: direct copy at the same index.
            ans[i] = nums[i];

            // Second half: same value, placed len positions ahead.
            // e.g. nums[0] goes to ans[0] AND ans[0 + len].
            ans[i + len] = nums[i];
        }

        return ans;
    }
}