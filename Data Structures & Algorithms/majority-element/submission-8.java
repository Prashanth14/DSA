class Solution {
    // Approach: count frequency of every number using a HashMap,
    // then check which one appears more than len/2 times (majority).
    // TC: O(n) -> one pass to build counts, one pass to check them
    // SC: O(n) -> map can hold up to n distinct keys in the worst case
    public int majorityElement(int[] nums) {
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each number.
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Check each number's count — majority means count > len/2.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                return entry.getKey();
            }
        }

        // Problem guarantees a majority always exists, so this
        // line is never actually reached (fallback only).
        return -1;
    }
}