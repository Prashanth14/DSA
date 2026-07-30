class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;

        int left = 0, right = 1;
        int maxProfit = 0;

        while(right < len){
            if(prices[left] > prices[right]){
                left = right;
            }else if(prices[left] < prices[right]){
                maxProfit = Math.max(maxProfit, prices[right]-prices[left]);
            }
            right++;
        }
        return maxProfit;
    }
}
