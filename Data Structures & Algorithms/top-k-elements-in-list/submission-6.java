class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer>[] freq = new List[len+1];

        for(int i = 0; i<len+1; i++){
            freq[i] = new ArrayList<>();
        }

        for(int i =0; i<len; i++){
            count.put(nums[i], count.getOrDefault(nums[i], 0)+1);
        }

        for(Map.Entry<Integer, Integer> entry: count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        int freqLen = freq.length;
        int index = 0;

        for(int i = freqLen - 1; i > 0 && index < k ; i--){
            for(int n: freq[i]){
                res[index++] = n;
                if(index == k){
                    return res;
                }
            }
        }
        return res;
    }
}
