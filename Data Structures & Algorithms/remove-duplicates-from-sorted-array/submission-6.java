class Solution {
    // TC: O(n) - single pass through the array with pointer j
    // SC: O(1) - modifies array in-place, only two pointers used
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len == 1) return len; // single element is trivially "unique"

        int i = 0, j = 1; // i = last unique element's index, j = scanner

        while(j < len){
            if(nums[i] != nums[j]){
                // found a new unique value, place it right after the last unique one
                nums[i+1] = nums[j];
                i++;
            }
             j++;
        }
        return i+1; // number of unique elements
    }
}