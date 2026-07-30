class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int len = nums.length, i =0, j = 1;

        while(j < len){
            if(nums[i] == nums[j]){
                j++;
            }else{
                nums[i+1] = nums[j];
                i++; j++;
                count++;
            }
        }
        return count;
    }
}