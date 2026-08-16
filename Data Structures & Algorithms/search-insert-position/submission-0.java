class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int res = len;


        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[mid] < target){
                left = mid+1;
            }else if(nums[mid] > target){
                res = mid;
                right = mid-1;
            }
        }
        return res;
    }
}