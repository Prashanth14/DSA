class Solution {
    // Approach: two-pointer, single pass in-place overwrite.
    // "i" scans every element. "k" tracks where the next
    // non-val element should go (also doubles as the running count).
    // Whenever nums[i] isn't val, keep it by writing it at position k,
    // then move k forward. Elements equal to val are simply skipped
    // (never written), so they get left behind/overwritten.
    //
    // TC: O(n) -> single pass, every element checked once
    // SC: O(1) -> only two extra variables (len, k), no extra array
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int k = 0; // next write position + count of valid elements so far

        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i]; // keep this element, advance write pointer
            }
            // if nums[i] == val, do nothing -> effectively "removed"
        }

        return k; // k = count of elements not equal to val
    }
}