class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0, right = len-1;

        while(right < len){
            int sum = numbers[left] + numbers[right];
            if( sum == target){
                return new int[]{left+1, right+1};
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{};
    }
}
