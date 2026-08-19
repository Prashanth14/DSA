class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int max = 0;
        int sum = 0;

        for(int w : weights){
            max = Math.max(w, max);
            sum += w;
        }

        int left = max, right = sum;

        while(left < right){
            int mid = left + (right - left)/2;
            int requiredDays = 1;
            int currentLoad = 0;

            for(int w : weights){
                if(currentLoad + w <= mid){
                    currentLoad += w;
                }else{
                    requiredDays++;
                    currentLoad = w;
                }
            }

            if(requiredDays <= days){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}