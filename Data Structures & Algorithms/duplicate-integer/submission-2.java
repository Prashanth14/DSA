class Solution {
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
       
        for(int i =0; i<len; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
                }else{
                    return true;
                }
            }
        return false;
    }
}