class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;

        int left = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;

        for(int right = 0; right < len; right++){

            sum += nums[right];
            while(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }
}