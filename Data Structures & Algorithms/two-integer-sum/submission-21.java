class Solution {
    // Brute force approach: for every element nums[i], scan the remaining
    // elements to its right (j = i+1 .. len-1) looking for a partner that
    // sums to target. Starting j at i+1 (instead of 0 or 1) means:
    //   - no need for an i != j check (j is always > i)
    //   - each pair {i, j} is examined exactly once
    //   - the smaller index is always returned first, by construction
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;

        // Outer loop: fix the first number, nums[i]
        for (int i = 0; i < len; i++) {
            // Inner loop: look for a second number after i that completes the sum
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    // Found the unique valid pair; return immediately
                    return new int[]{i, j};
                }
            }
        }

        // No valid pair found (won't happen given the problem's guarantee
        // that exactly one solution exists)
        return new int[]{};
    }
}

// Time Complexity:  O(n^2)
//   - Outer loop runs n times; for each i, inner loop runs (n - i - 1) times.
//   - Total comparisons = (n-1) + (n-2) + ... + 1 + 0 = n(n-1)/2 -> O(n^2),
//     with roughly half the comparisons of the j=1 version (no wasted i==j checks).
//
// Space Complexity: O(1)
//   - Only a fixed-size (length-2) output array and a few loop variables are
//     used; no auxiliary data structures that grow with input size.