class Solution {
    public int findDuplicate(int[] nums) {
        // Solution with Time complexity O(n) and Space Complexity of O(1)
        for(int num: nums){
            int idx = Math.abs(num) - 1;
            if(nums[idx] < 0){
                return Math.abs(num);
            }
            nums[idx] *= -1; 
        }
        return -1;
    }
}
