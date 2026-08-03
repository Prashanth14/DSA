class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int len = nums.length;
        for(int i =0; i <len; i++){
            int sum = 0;
            for(int j = i; j < len; j++){
                sum += nums[j];
                if(sum == k){
                    result++;
                }
            }
        }
        return result;
    }
}