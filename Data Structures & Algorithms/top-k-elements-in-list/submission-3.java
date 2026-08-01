class Solution {
    // Approach: count frequencies with a HashMap, convert entries to a
    // list, sort by frequency descending, take the first k keys.
    //
    // TC breakdown:
    //   - counting loop: O(n)              (n = nums.length)
    //   - building the list: O(d)          (d = number of distinct elements)
    //   - sorting the list: O(d log d)     (dominant term)
    //   - extracting top k: O(k)
    //   Overall: O(n + d log d) -> simplified to O(n log n) worst case
    //            (when almost every element is distinct, d ≈ n)
    //
    // SC breakdown:
    //   - map: O(d)
    //   - list: O(d)
    //   - result array: O(k)
    //   Overall: O(n) worst case (d can be up to n)
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each number. O(n).
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Convert map entries into a sortable list. O(d).
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        // Sort by frequency, descending. O(d log d).
        list.sort((a, b) -> b.getValue() - a.getValue());

        // Take the top k keys (highest frequency first). O(k).
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }
}