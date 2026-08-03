class Solution {
    // Approach: in-place index marking (no extra HashSet needed).
    // The answer must be in range [1, len+1] - can't be larger, since
    // with only 'len' numbers you can't cover 1..len AND leave a
    // smaller gap unfilled.
    //
    // Step 1: replace anything out of range [1, len] with a sentinel
    // (len+1) - these values can never be "the answer" or a valid
    // index to mark, so get them out of the way.
    //
    // Step 2: for each number still in range, use its VALUE as an
    // index into the array itself, and mark that position as "seen"
    // by negating it. Math.abs() recovers the true value even if this
    // cell was already negated earlier by a different index's turn.
    //
    // Step 3: first index still positive = first number never marked
    // as seen = the answer.
    //
    // TC: O(n) -> three separate linear passes
    // SC: O(1) -> no extra array/set, marking is done in-place on nums
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        // Step 1: replace invalid/out-of-range values with a safe sentinel.
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len) {
                nums[i] = len + 1;
            }
        }

        // Step 2: mark presence of each number by negating its target index.
        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]); // recover true value even if already negated

            if (num > len) continue; // sentinel value, nothing to mark

            if (nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1]; // mark index (num-1) as "seen"
            }
        }

        // Step 3: first still-positive index -> that number was never seen.
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // All of 1..len were present -> next integer is the answer.
        return len + 1;
    }
}