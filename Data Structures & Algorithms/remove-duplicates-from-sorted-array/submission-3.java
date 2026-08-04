class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len == 1) return len;

        int i = 0, j = 1;
        int cnt = 0;

        while(j < len){
            if(nums[i] != nums[j]){
                cnt++;
                nums[i+1] = nums[j];
                i++;
                j++;
            }else{
                j++;
            }
        }
        return cnt+1;
    }
}