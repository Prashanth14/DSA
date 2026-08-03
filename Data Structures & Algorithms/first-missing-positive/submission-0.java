class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int num: nums){
            set.add(num);
        }

        for(int i = 1 ; i <= len; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return len+1;
    }
}