class Solution {
    public int[] twoSum(int[] nums, int target) {
        // brute force method 
        int len = nums.length;

        for(int i = 0; i<len; i++){
            for(int j =1; j <len; j++){
                if(nums[i] + nums[j] == target && i != j){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
