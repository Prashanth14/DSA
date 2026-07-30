class Solution {
    public boolean hasDuplicate(int[] nums) {
        int len = nums.length;
        HashSet<Integer> set = new HashSet<Integer>();

        for(int i =0; i<len; i++){
            set.add(nums[i]);
        }

        return (len == set.size()? false: true);
    }
}