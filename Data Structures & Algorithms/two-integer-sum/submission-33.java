class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Optimal one-pass HashMap approach.
        // Idea: while walking through the array, keep a note of every
        // number we've already seen along with its index (value -> index).
        // For each new number, check if the "partner" it needs
        // (target - current number) was already seen before.

        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // stores: number -> its index

        for (int i = 0; i < len; i++) {

            // the number we need to find to complete the pair
            int x = target - nums[i];

            // check FIRST, before adding the current number to the map.
            // this guarantees:
            //   1) we never match a number with itself
            //   2) map.get(x) is always an earlier (smaller) index than i,
            //      since only past numbers exist in the map at this point
            if (map.containsKey(x)) {
                return new int[]{map.get(x), i}; // smaller index first, then current index
            }

            // partner not found yet, so remember this number for future steps
            map.put(nums[i], i);
        }

        // no pair found (won't happen given problem's guarantee of one answer)
        return new int[]{};
    }
}

// Time Complexity: O(n)
//   - single pass through the array (n iterations)
//   - each HashMap containsKey/get/put is O(1) on average

// Space Complexity: O(n)
//   - in the worst case, the map stores almost all n numbers before finding a match