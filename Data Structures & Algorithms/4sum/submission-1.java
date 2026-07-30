class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        //Bruteforce
        int len = nums.length;
        for(int i =0; i<len; i++){
            for(int j = i+1; j < len; j++){
                for(int k = j+1; k<len; k++){
                    for(int l = k+1; l<len; l++){
                        if((long)nums[i]+nums[j]+nums[k]+nums[l] == target){
                            res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
       return new ArrayList<>(res);
    }
}