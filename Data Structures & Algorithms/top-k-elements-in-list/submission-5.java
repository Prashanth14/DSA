class Solution {
    // Approach: count frequencies with a HashMap, then use a min-heap
    // (bounded to size k) to keep only the top k most frequent numbers.
    // Whenever the heap grows past k, evict the smallest frequency —
    // what survives at the end is the top k.
    //
    // TC breakdown:
    //   - counting loop: O(n)                (n = nums.length)
    //   - building the heap: O(d log k)      (d = distinct elements,
    //                                          each offer/poll is O(log k)
    //                                          since heap capped at size k)
    //   - draining the heap: O(k log k)
    //   Overall: O(n + d log k) -> better than O(n log n) when k << d
    //
    // SC breakdown:
    //   - map: O(d)
    //   - heap: O(k)  (bounded, never grows past k)
    //   - result array: O(k)
    //   Overall: O(d)
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each number. O(n).
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Min-heap on frequency -> smallest frequency sits at the root,
        // ready to be evicted first.
        PriorityQueue<Map.Entry<Integer, Integer>> pq = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Push every entry in; whenever heap exceeds size k, evict the
        // smallest -> only the top k frequencies survive.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Drain remaining k entries into the result (order doesn't matter).
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }
}