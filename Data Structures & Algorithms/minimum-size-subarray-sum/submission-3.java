class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int minWindowSize = Integer.MAX_VALUE;
        int sum = 0;

        for(int right = 0; right < len; right++){
            sum += nums[right]; 

            while(sum >= target){ 
                minWindowSize = Math.min(minWindowSize, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minWindowSize == Integer.MAX_VALUE? 0: minWindowSize;
    }
}