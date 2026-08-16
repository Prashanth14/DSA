class Solution {
    public int mySqrt(int x) {
        long left = 0, right = x;
        long res = 0;

        while(left <= right){
            long mid = left + (right - left)/2;
            long square = mid * mid;

            if(square < x){
                res = mid;
                left = mid + 1;
            }else if(square > x){
                right = mid-1;
            }else{
                return (int)mid;
            }
        }
        return (int)res;
    }
}