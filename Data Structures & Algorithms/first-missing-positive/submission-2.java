class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for(int i = 0; i < len; i++){
            if(nums[i] <= 0 || nums[i] > len){
                nums[i] = len + 1;
            }
        }

        for(int i = 0; i < len; i++){
            int num = Math.abs(nums[i]);

            if(num > len) continue;

            if(nums[num-1] > 0){
                nums[num -1] = -nums[num -1];
            }
        }

        for(int i = 0; i<len; i++){
            if(nums[i] > 0){
                return i+1;
            }
        }

        return len+1;
    }
}