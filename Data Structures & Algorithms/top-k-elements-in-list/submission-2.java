class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < len; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        for(int i = 0; i< k; i++){
            result[i] = list.get(i).getKey();
        }
        return result;
    }
}
