class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        int res = 0;

        while(left <= right){
            int mid = left + (right - left)/2;
            long square = (long)mid * mid;

            if(square < x){
                res = mid;
                left = mid + 1;
            }else if(square > x){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return res;
    }
}