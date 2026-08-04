class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        if(nums == null || len < 4) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i< len; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;

            for(int j = i+1; j < len; j++){
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int left = j +1;
                int right = len-1;

                while(left < right){
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];

                    if(sum == target){
                        result.add(Arrays.asList(nums[i],nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        while(left < right && nums[left] == nums[left-1]){
                            left++;
                        }
                    }else if(sum > target){
                        right--;
                    }else{
                        left++;
                    }
                }
            }
        }
        return result;
    }
}