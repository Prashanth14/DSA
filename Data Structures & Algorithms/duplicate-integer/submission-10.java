class Solution {
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;
        if(len == 0 ) return false;
        Set<Integer> set = new HashSet<>();

        for(int i =0; i < len; i++){
            set.add(nums[i]);
        }

        if(set.size() != len) {
            return true;
        }
        return false;
    }
}