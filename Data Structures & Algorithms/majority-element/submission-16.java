class Solution {
    public int majorityElement(int[] nums) {
        int majorityEle = 0;
        int count = 0;

        for(int num: nums){
            if(count == 0){
                majorityEle = num;
            }

            if(majorityEle == num){
                count += 1;
            }else{
                count -= 1;
            }
        }
        return majorityEle;
    }
}