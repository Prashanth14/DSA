class Solution {
    // Approach: prefix/suffix product arrays (no division).
    // leftProd[i] = product of everything BEFORE index i.
    // rightProd[i] = product of everything AFTER index i.
    // res[i] = leftProd[i] * rightProd[i] -> everything except nums[i].
    //
    // TC: O(n) -> three separate O(n) passes (left, right, combine)
    // SC: O(n) -> leftProd + rightProd arrays (auxiliary, not counting
    //     the required output array itself)
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProd = new int[len];
        int[] rightProd = new int[len];

        leftProd[0] = 1; // nothing to the left of index 0
        for (int i = 1; i < len; i++) {
            leftProd[i] = leftProd[i - 1] * nums[i - 1]; // product before i
        }

        rightProd[len - 1] = 1; // nothing to the right of the last index
        for (int i = len - 2; i >= 0; i--) {
            rightProd[i] = rightProd[i + 1] * nums[i + 1]; // product after i
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = leftProd[i] * rightProd[i]; // combine: before * after
        }
        return res;
    }
}