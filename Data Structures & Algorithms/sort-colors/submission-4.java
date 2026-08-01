class Solution {
    public void sortColors(int[] nums) {
        //Use HashMap
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < len; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        int x = 0;
        for(int i = 0; i < 3; i++){
            int count = map.getOrDefault(i, 0);
            for(int c = 0; c < count; c++){
                nums[c + x] = i;
            }
            x += count;
        }
    }
}