class Solution {
    // Approach: counting sort. Count occurrences of each color (0,1,2)
    // using a HashMap, then overwrite the array in order: all 0s,
    // then all 1s, then all 2s, using the counts.
    // TC: O(n) -> one pass to count, one pass to overwrite (bounded by 3*n)
    // SC: O(1) -> map only ever holds at most 3 keys (0,1,2), constant size
    public void sortColors(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        // Count how many times each color appears.
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int x = 0; // next write position in nums

        // Overwrite nums in color order: 0s, then 1s, then 2s.
        // Outer loop: fixed 3 iterations (colors 0,1,2) -> O(3) = O(1)
        // Inner loop: runs 'count' times per color. Across all 3 colors,
        // count(0) + count(1) + count(2) = len (every element counted once)
        // -> total inner loop work across ALL iterations = O(n)
        // Combined: O(1) * outer + O(n) total inner = O(n) overall
        for (int i = 0; i < 3; i++) {
            int count = map.getOrDefault(i, 0); // 0 if this color never appeared
            for (int c = 0; c < count; c++) {
                nums[c + x] = i; // write 'count' copies of color i
            }
            x += count; // move write position past what was just written
        }
    }
}