class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for(int i=0; i<len; i++){
            if(i > 0 && (nums[i] == nums[i-1])) i++;
            for(int j = i+1; j<len; j++){
                int p = j+1, q = len-1;

                while(p < q){
                    long sum = (long)nums[i] + nums[j]+ nums[p] + nums[q];
                
                    if(sum < target){
                        p++;
                    }else if(sum > target){
                        q--;
                    }else if(sum == target){
                        set.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        p++;
                        q--;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}