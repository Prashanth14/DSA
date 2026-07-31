class Solution {
    public int majorityElement(int[] nums) {
        // I can solve using sorting as well
        int len = nums.length;
        if(len == 1) return nums[0];
        Arrays.sort(nums);
        int count = 1;
        int max = -1;

        for(int i = 1; i<len; i++){
            if(nums[i-1] == nums[i]){
                count++;
                max = Math.max(count, max);
                if(max > len/2) return nums[i-1];
            }else{
                count = 1;
            }
        }
        return -1;
    }
}