class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid =0, high = nums.length-1;

        while(mid <= high){
            if(nums[mid] == 0){
                int t = nums[mid];
                nums[mid] = nums[low];
                nums[low] = t;
                mid++;
                low++;
            }else if(nums[mid] == 1){
                mid++;
            }else{
                int t = nums[mid];
                nums[mid] = nums[high];
                nums[high] = t;
                high--;
            }
        }
    }
}