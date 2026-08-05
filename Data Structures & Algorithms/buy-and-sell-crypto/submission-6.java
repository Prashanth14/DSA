class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int len = prices.length;
        if(len == 1) return 0;

        int left = 0, right = left+1;

        while(left < right && right < len){
            if(prices[left] > prices[right]){
                left = right;
                right++;
            }else{
                profit = Math.max(profit, prices[right] - prices[left]);
                right++;
            }
        }
        return profit;
    }
}
