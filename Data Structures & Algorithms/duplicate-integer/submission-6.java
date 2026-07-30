class Solution {
    // ---------------------------------------------------------------
    // Approach: Brute force — compare every pair of elements.
    // For each element, check it against every element that comes
    // after it. If any two match, a duplicate exists.
    //
    // Overall Time Complexity: O(n^2) -> nested loop, comparing every
    //                                    pair (i, j) where j > i
    // Overall Space Complexity: O(1)  -> no extra data structure used,
    //                                    only a couple of index variables
    // ---------------------------------------------------------------
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;

        // Outer loop: pick each element as a "candidate" to check.
        for (int i = 0; i < len; i++) {

            // Inner loop: compare candidate against every element after it.
            // Starting at i+1 avoids comparing an element with itself and
            // avoids redundant re-checking of earlier pairs.
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == nums[j]) {
                    // Found a matching pair -> duplicate exists.
                    return true;
                }
            }
        }

        // No matching pair found in any comparison -> all elements unique.
        return false;
    }
}