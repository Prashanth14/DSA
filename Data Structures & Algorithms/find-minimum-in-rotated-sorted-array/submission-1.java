class Solution {
    public int isSorted(int[] arr){
        int len = arr.length;
        for(int i = 1; i <len; i++){
            if(!(arr[i-1] < arr[i])){
                return i;
            }
        }
        return 0;
    }

    public int findMin(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        return nums[isSorted(nums)];
    }
}
