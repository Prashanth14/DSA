class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< len; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > len/3){
                result.add(entry.getKey());
            }
        }
        return result;
    }
}