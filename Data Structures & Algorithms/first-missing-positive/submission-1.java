class Solution {
    // Approach: put all numbers in a HashSet for O(1) lookup, then
    // check 1, 2, 3, ... in order until one is missing.
    //
    // Key insight: the answer must be in range [1, len+1]. If nums
    // contains all of 1..len somewhere, the answer is len+1 (the very
    // next integer); it can't be anything larger, since with only
    // 'len' numbers, you can't possibly cover 1..len AND leave a gap
    // smaller than len+1 unfilled.
    //
    // TC: O(n) -> building the set is O(n), checking 1..len is O(n)
    // SC: O(n) -> HashSet stores up to n elements
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();

        // Store every number (positives, negatives, zero - doesn't matter).
        for (int num : nums) {
            set.add(num);
        }

        // Check 1, 2, 3, ... len in order - first one missing is the answer.
        for (int i = 1; i <= len; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        // All of 1..len were present -> next integer is the answer.
        return len + 1;
    }
}