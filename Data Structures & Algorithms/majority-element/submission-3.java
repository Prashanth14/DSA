class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int len = nums.length;
        for(int n: nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        for(int n: nums){
            if(map.get(n) > len/2) return n;
        }
        return 0;
    }
}