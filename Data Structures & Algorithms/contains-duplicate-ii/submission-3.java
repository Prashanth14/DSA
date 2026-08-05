class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();

        int l = 0;

        for(int r = 0; r < len; r++){
            if((r - l) > k){
                set.remove(nums[l]);
                l++;
            }

            if(set.contains(nums[r])){
                return true;
            }

            set.add(nums[r]);
        }
        return false;
    }
}