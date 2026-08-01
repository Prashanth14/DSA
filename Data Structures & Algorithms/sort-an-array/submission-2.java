class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;

        for(int i = 0; i <len; i++){
            for(int j  = 0; j <len; j++){
                if(nums[i] < nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}