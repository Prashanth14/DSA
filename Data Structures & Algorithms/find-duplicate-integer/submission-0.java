class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> st = new HashSet<>();
        int n = nums.length;

        for(int i = 0; i< n; i++){
            if(!st.contains(nums[i])){
                st.add(nums[i]);
            }else{
                return nums[i];
            }
        }
        return 0;
    }
}
