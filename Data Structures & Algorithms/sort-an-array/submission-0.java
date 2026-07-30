class Solution {
    public int[] sortArray(int[] nums) {
        //bruteforce
        int len = nums.length;
        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                if(nums[i] > nums[j]){
                    int t = nums[i];
                    nums[i] = nums[j];
                    nums[j] = t;
                }
            }
        }
        return nums;
    }
}