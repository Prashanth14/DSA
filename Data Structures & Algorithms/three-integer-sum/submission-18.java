class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if(nums == null || len < 3) return new ArrayList<>();

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i< len; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int left = i+1;
            int right = len-1;

            while(left < right){
                int sum  = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left -1]){
                        left++;
                    }
                }else if (sum > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }
}
