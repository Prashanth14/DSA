class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each number. O(n).
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            pq.offer(entry);
            if(pq.size() > k){
                pq.poll();
            }
        }

        for(int i = 0; i<k; i++){
            result[i] = pq.poll().getKey();
        }
        return result;
    }
}
