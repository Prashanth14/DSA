class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        HashSet<Integer> window = new HashSet<>();
        
        for(int i =0; i<len; i++){
            //Shrink the Window 
            if(i > k){
                window.remove(nums[i-k-1]);
            }

            if(window.contains(nums[i])){
                return true;
            }

            window.add(nums[i]);
        }
        return false;
    }
}