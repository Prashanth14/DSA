class Solution {
    // TC: O(n) - single pass, each pointer moves at most n times total
    // SC: O(1) - only two pointers used, no extra space
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0, right = len-1; // pointers at both ends of sorted array

        while(left < right){
            int sum = numbers[left] + numbers[right];
            if( sum == target){
                return new int[]{left+1, right+1}; // 1-indexed result
            }else if(sum < target){
                left++;  // sum too small, move left pointer right to increase sum
            }else{
                right--; // sum too big, move right pointer left to decrease sum
            }
        }
        return new int[]{}; // no solution (won't happen per problem guarantee)
    }
}