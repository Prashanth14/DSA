class Solution {
    public int[] twoSum(int[] nums, int target) {
        // //1. Bruteforce method -> Time complexity O(n^2)
        // int [] res = new int[2];
        // int len = nums.length;
        // for(int i = 0; i<len; i++){
        //     for(int j = i+1; j <len; j++){
        //         if(nums[i] + nums[j] == target){
        //             res[0] = i;
        //             res[1] = j;
        //         }
        //     }
        // }
        // return res;


        //2. using HashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int[] res = new int[2];

        for(int i = 0; i<len; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                res[0] = map.get(diff);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
