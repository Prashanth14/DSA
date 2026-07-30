class Solution {
    public int[] twoSum(int[] nums, int target) {
        // using HashMap
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<len; i++){
            int x = target-nums[i];
            if(map.containsKey(x) && i != map.get(x) ){
                return new int[]{map.get(x), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
