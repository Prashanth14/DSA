class Solution {
    // Approach: two-pass, O(1) extra space, no division.
    // Phase 1: res[i] = product of everything BEFORE index i.
    // Phase 2: sweep right to left, multiplying in a running
    // rightProd (product of everything AFTER index i), then
    // updating rightProd to include nums[i] for the next step.
    //
    // TC: O(n) -> two separate linear passes
    // SC: O(1) extra -> only 'rightProd' as auxiliary state;
    //     res[] is the required output, not counted as extra space
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        // Phase 1: left products, stored directly into res.
        res[0] = 1; // nothing to the left of index 0
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1]; // product of everything before i
        }

        // Phase 2: multiply in right products using a single running value.
        int rightProd = 1; // nothing to the right of the last index, initially
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= rightProd;   // combine: (left product) * (right product so far)
            rightProd *= nums[i];  // extend rightProd to include this index, for the next (leftward) step
        }

        return res;
    }
}