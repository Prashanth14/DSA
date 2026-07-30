class Solution {
    public boolean hasDuplicate(int[] nums) {
        // solve using sorting the array
        int len = nums.length;
        if(len == 0) return false;
        Arrays.sort(nums);

        for(int i = 1; i <len; i++){
            if(nums[i-1] == nums[i]){
                return true;
            }
        }

        return false;
    }
}