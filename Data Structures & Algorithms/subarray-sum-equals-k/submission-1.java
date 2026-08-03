class Solution {
    // Approach: brute force. For every starting index i, extend the
    // subarray one element at a time (j = i, i+1, ..., len-1), keeping
    // a running sum, and count every time that sum equals k.
    // TC: O(n^2) -> nested loop, checking every possible subarray
    // SC: O(1) -> just a running sum and a counter, no extra structure
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int sum = 0; // running sum of subarray starting at i
            for (int j = i; j < len; j++) {
                sum += nums[j]; // extend subarray to include nums[j]
                if (sum == k) {
                    result++; // found a subarray [i..j] summing to k
                }
            }
        }

        return result;
    }
}