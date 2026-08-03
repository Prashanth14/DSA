class Solution {
    // Approach: extended Boyer-Moore Voting (2 candidates instead of 1),
    // since at most 2 elements can ever appear more than n/3 times
    // (3 elements each over n/3 would exceed n total).
    //
    // Phase 1 (voting): track 2 candidate/count pairs. On each element:
    // claim an empty slot if available, extend a matching candidate's
    // count, or if neither applies, "cancel" one vote from both
    // candidates (simulates removing 3 distinct elements together).
    //
    // Phase 2 (verify): candidates surviving the vote are NOT guaranteed
    // to actually exceed n/3 (unlike Majority Element I, no majority is
    // guaranteed here) - recount their real occurrences and only keep
    // the ones that truly qualify.
    //
    // TC: O(n) -> two linear passes (voting + verification)
    // SC: O(1) -> only a fixed number of int variables, no map/array
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;

        int mj1 = 0;
        int count1 = 0;
        int mj2 = 0;
        int count2 = 0;

        // Phase 1: voting
        for (int x : nums) {
            if (count1 == 0 && x != mj2) {
                mj1 = x;      // claim empty slot 1
                count1 = 1;
            } else if (count2 == 0 && x != mj1) {
                mj2 = x;      // claim empty slot 2
                count2 = 1;
            } else if (x == mj1) {
                count1++;     // extend existing candidate 1
            } else if (x == mj2) {
                count2++;     // extend existing candidate 2
            } else {
                // x is distinct from both candidates, and both slots
                // are occupied -> cancel one vote from each
                count1--;
                count2--;
            }
        }

        // Phase 2: verify actual counts of surviving candidates
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (num == mj1) {
                cnt1++;
            } else if (num == mj2) {
                cnt2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (cnt1 > len / 3) result.add(mj1);
        if (cnt2 > len / 3) result.add(mj2);

        return result;
    }
}