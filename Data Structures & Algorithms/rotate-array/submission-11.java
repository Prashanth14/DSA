class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;

        reverse(nums, 0 , len-k-1);
        reverse(nums,len-k, len-1);
        reverse(nums, 0, len-1);
    }

    public void reverse(int[] nums, int i, int j){
        while(i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}