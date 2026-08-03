class Solution {
    // Approach: count frequency of every number using a HashMap,
    // then keep only the ones that appear more than n/3 times.
    // TC: O(n) -> one pass to count, one pass to check (d <= n entries)
    // SC: O(n) -> map can hold up to n distinct keys in the worst case
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each number.
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Keep numbers whose count exceeds n/3.
        // At most 2 numbers can ever satisfy this (mathematical
        // guarantee: 3+ numbers each over n/3 would exceed n total).
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 3) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}