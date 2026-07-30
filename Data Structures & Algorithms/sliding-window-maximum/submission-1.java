class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        int[] out = new int[len-k+1];

        for(int i = 0; i<= len-k; i++){
            int max = nums[i];
            for(int j = i; j < i+k; j++){
                max = Math.max(max, nums[j]);
            }
            out[i] = max;
        }
      return out;
    }
}
