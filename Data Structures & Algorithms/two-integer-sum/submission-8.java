class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
        
        for(int i = 0; i<len; i++){
            map.put(nums[i], i);
        }

        for(int i = 0; i<len; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff) && (i != map.get(diff))){
                return new int[]{i, map.get(diff)};
            }
        }
        return new int[]{};
    }
}
