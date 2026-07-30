class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
        
        for(int i = 0; i<len; i++){
            map.put(nums[i], i);
        }

        for(int i = 0; i<len; i++){
            if(map.containsKey(target - nums[i]) && (i != map.get(target - nums[i]))){
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                return res;
            }
        }
        return new int[]{};
    }
}
