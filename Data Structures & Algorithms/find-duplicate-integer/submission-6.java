class Solution {
    // Approach: in-place sign marking (value-as-index cycle detection)
    // TC: O(n) - single pass through the array
    // SC: O(1) - no extra data structure, reuses sign bit of existing elements
    public int findDuplicate(int[] nums) {
        for(int num : nums){
            // every value is in range [1, n], so it can double as an index
            int idx = Math.abs(num) - 1;

            // if the target cell is already negative, it was visited before
            // by some earlier value -> that shared target index means a duplicate
            if(nums[idx] < 0){
                return Math.abs(num);
            }

            // mark this index as visited by flipping its sign
            nums[idx] *= -1;
        }
        return -1; // unreachable given problem guarantees a duplicate exists
    }
}