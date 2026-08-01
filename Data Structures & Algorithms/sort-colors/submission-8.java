class Solution {
    // Approach: Dutch National Flag (3-pointer, one-pass in-place).
    // low  = boundary marking end of the "confirmed 0s" zone
    // mid  = current element being examined
    // high = boundary marking start of the "confirmed 2s" zone
    // TC: O(n) -> single pass, mid visits each position once
    // SC: O(1) -> only 3 pointers + a temp variable, no extra array/map
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // belongs in 0-zone: swap into low boundary, advance both
                // (swapped-in value is guaranteed 0 or 1 -> safe to skip)
                swap(nums, mid, low);
                low++;
                mid++;
            } else if (nums[mid] == 2) {
                // belongs in 2-zone: swap into high boundary
                // do NOT advance mid -> swapped-in value is unexamined
                swap(nums, mid, high);
                high--;
            } else {
                // nums[mid] == 1, already in correct zone -> just move on
                mid++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}