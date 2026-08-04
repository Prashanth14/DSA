class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len == 1) return len;

        int i = 0, j = 1;

        while(j < len){
            if(nums[i] != nums[j]){
                nums[i+1] = nums[j];
                i++;
                j++;
            }else{
                j++;
            }
        }
        return i+1;
    }
}