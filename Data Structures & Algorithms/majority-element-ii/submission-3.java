class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;

        int mj1 = 0;
        int count1 = 0;
        int mj2 = 0;
        int count2 = 0;

        for(int x : nums){
            if(count1 == 0 && x != mj2){
                mj1 = x;
                count1 = 1;
            }else if(count2 == 0 && x != mj1){
                mj2 = x;
                count2 = 1;
            }else if (x == mj1){
                count1++;
            }else if(x == mj2){
                count2++;
            }else{
                count1--;
                count2--;
            }
        }

        int cnt1 = 0, cnt2 = 0;
        for(int num : nums){
            if(num == mj1){
                cnt1++;
            }else if(num == mj2){
                cnt2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if(cnt1 > len/3) result.add(mj1);
        if(cnt2 > len/3) result.add(mj2);

        return result;
    }
}