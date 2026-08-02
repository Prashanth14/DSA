class Solution {
    // Approach: bucket sort by frequency. Since a number can appear at
    // most 'len' times, frequency is bounded -> use direct indexing
    // (bucket = frequency) instead of sorting or a heap.
    //
    // TC: O(n) -> counting O(n), bucket placement O(d) (d = distinct
    //     elements), bucket walk visits at most n buckets + d numbers.
    //     No comparisons/sorting anywhere.
    // SC: O(n) -> count map + freq buckets array
    public int[] topKFrequent(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> count = new HashMap<>();

        // freq[f] = list of numbers that occurred exactly f times.
        // Max possible frequency is len, so len+1 buckets needed (0..len).
        List<Integer>[] freq = new List[len + 1];

        for (int i = 0; i < len + 1; i++) {
            freq[i] = new ArrayList<>(); // must init, else null -> NPE on iteration
        }

        // Count occurrences of each number. O(n).
        for (int i = 0; i < len; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        // Place each number into the bucket matching its frequency. O(d).
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        int freqLen = freq.length;
        int index = 0;

        // Walk buckets from highest frequency down to 1, collecting
        // numbers until res is full.
        for (int i = freqLen - 1; i > 0 && index < k; i--) {
            for (int n : freq[i]) {
                res[index++] = n;
                if (index == k) {
                    return res; // early exit once k numbers collected
                }
            }
        }
        return res;
    }
}