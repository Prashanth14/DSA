class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        //brute force
        List<List<Integer>> result = new ArrayList<>();

        int len = nums.length;

        for(int i = 0; i< len; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;

            for(int j = i+1; j<len; j++){
                if(j > i+1 && nums[j] == nums[j-1]) continue;

                for(int k =j+1; k<len; k++){
                    if(k > j+1 && nums[k] == nums[k-1]) continue;

                    if(nums[i]+ nums[j]+ nums[k] == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }
}
