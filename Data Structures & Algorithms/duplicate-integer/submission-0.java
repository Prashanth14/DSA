class Solution {
    public boolean hasDuplicate(int[] nums) {
        //1.brute force method, if I use this method time limit is Exceeding for some test cases
        //-> Time Complexity O(n^2)
        int len = nums.length;
        // for(int i =0; i<len; i++){
        //     for(int j= i+1; j <len; j++){
        //         if(nums[i] == nums[j]){
        //             return true;
        //         }
        //     }
        // }
        // return false;

    //2. using HashMap -> Time Complexity O(n)
    // HashMap<Integer, Integer> map = new HashMap<>();
    //store array elements and its count in the Map
    // for (int i =0; i<len; i++){
    //     if(map.containsKey(nums[i]) && map.get(nums[i]) >= 1){
    //          return true;
    //     }
    //     map.put(nums[i], map.getOrDefault( nums[i], 0)+1);
    // }
    // return false;


    //3.Use Sorting -> Time Complexity O(nlogn)
    // Arrays.sort(nums);
    // for(int i = 0; i<len-1; i++){
    //     if(nums[i] == nums[i+1]){
    //         return true;
    //     }
    // }
    // return false;

    //4. Using HashSet -> Time Complexity O(n)
    HashSet<Integer> set = new HashSet<>();
    for(int num: nums){
        if(set.contains(num)){
            return true;
        }
        set.add(num);
    }
    return false;
    }
}
