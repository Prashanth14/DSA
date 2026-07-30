class Solution {
    public int[] productExceptSelf(int[] nums) {
        //1.  Bruteforce method -> O(n^2)
        // int len = nums.length;
        // int[] res = new int[len];
        
        // for(int i =0; i<len; i++){
        //     int prod = 1;
        //     for(int j = 0; j<len; j++){
        //         if(i != j){
        //             prod *= nums[j];
        //         }
        //     }
        //     res[i] = prod;
        // }
        // return res;

        //prefix and suffix with left and right product arrays
        int len = nums.length;
        

        // create left array and Right array
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = 1;
        for(int i = 1; i<len; i++){
            left[i] = left[i-1] * nums[i-1];
        } 

        right[len-1] = 1;
        for(int j = len-2; j >-1; j--){
            right[j] = right[j+1] * nums[j+1];
        }

        for(int i = 0; i<len; i++){
            nums[i] = left[i] * right[i];
        }
        return nums;
    }
}  
