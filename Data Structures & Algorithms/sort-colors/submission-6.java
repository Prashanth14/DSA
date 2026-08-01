class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int low = 0, mid = 0, high = len-1;

        while( mid <= high){
            if(nums[mid] == 0){
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low++] = temp;
                mid++;
            }else if(nums[mid] == 2){
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high--] = temp;
            }else{
                mid++;
            }
        }
    }
}