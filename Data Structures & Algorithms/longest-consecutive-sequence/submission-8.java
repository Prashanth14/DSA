class Solution {
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;

        Arrays.sort(nums);

        int i = 0;
        int counter = 1;
        int longestCounter = 1;
        while(i < len-1){
            if(nums[i] + 1 == nums[i+1]){
                counter++;
            }else if(nums[i+1] > nums[i] + 1){
                counter = 1;
            }
            longestCounter = Math.max(counter, longestCounter);
            i++;
        }
        return longestCounter;
    }
}
