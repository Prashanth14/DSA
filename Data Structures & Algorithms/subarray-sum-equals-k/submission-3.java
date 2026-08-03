class Solution {
    // Approach: prefix sum + HashMap ("notebook of positions visited").
    // Track running sum as we scan. For each position, check if an
    // EARLIER prefix sum equals (currentSum - k) - if so, everything
    // between that earlier point and now sums to exactly k.
    // Map stores COUNTS (not just presence), since the same prefix sum
    // can occur multiple times, and each occurrence = a separate valid
    // subarray ending at the current position.
    //
    // TC: O(n) -> single pass, O(1) average HashMap get/put per element
    // SC: O(n) -> map can hold up to n distinct prefix sum values
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumCountMap = new HashMap<>();

        // Seed with {0: 1} - represents "prefix sum of 0, before the
        // array even starts." Needed to correctly catch subarrays that
        // start right at index 0 (their "before" sum is 0 by definition).
        sumCountMap.put(0, 1);

        int result = 0;
        int prefixSum = 0; // running sum = "current position on the number line"

        for (int num : nums) {
            prefixSum += num; // take a step -> move to new position

            // Have we stood at (prefixSum - k) before? If yes, every
            // occurrence represents one valid subarray ending here.
            if (sumCountMap.containsKey(prefixSum - k)) {
                result += sumCountMap.get(prefixSum - k);
            }

            // Record that we've now visited this prefix sum (increment
            // its count, since we may revisit the same sum again later).
            sumCountMap.put(prefixSum, sumCountMap.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }
}