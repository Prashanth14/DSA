class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Set<Integer> numsSet = new HashSet<>();

        for(int num: nums){
            numsSet.add(num);
        }

        int longest = 1;

        for(int num : numsSet){
            if(!numsSet.contains(num-1)){
                int length = 1;
                while(numsSet.contains(num + length)){
                    length += 1;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}
