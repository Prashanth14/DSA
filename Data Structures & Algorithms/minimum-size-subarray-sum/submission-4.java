class Solution {
    // Pattern: Sliding window (variable size)
    // TC: O(n) - right moves forward n times; left moves forward at most n times total across all iterations
    // SC: O(1) - only pointers and running sum used
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int minWindowSize = Integer.MAX_VALUE; // no valid window found yet
        int sum = 0;

        for(int right = 0; right < len; right++){
            sum += nums[right]; // expand window by including nums[right]

            // window sum meets/exceeds target: shrink from left as much as possible
            // while still valid, tracking the smallest valid window seen
            while(sum >= target){
                minWindowSize = Math.min(minWindowSize, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        // if never updated, no subarray sums to >= target
        return minWindowSize == Integer.MAX_VALUE ? 0 : minWindowSize;
    }
}