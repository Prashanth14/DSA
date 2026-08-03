class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProd = new int[len];
        int[] rightProd = new int[len];

        leftProd[0] = 1;
        for(int i = 1; i <len; i++){
            leftProd[i] =leftProd[i-1] * nums[i-1]; 
        }

        rightProd[len-1] = 1;
        for(int i = len-2; i >=0 ; i--){
            rightProd[i] = rightProd[i+1] * nums[i+1]; 
        }

        int[] res = new int[len];
        for(int i = 0; i< len; i++){
            res[i] = leftProd[i] * rightProd[i];
        }
        return res;
    }
}  
