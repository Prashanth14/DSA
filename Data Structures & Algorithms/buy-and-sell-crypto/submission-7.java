class Solution {
    // Pattern: Two pointers (same direction) - left tracks index of min price
    // seen so far, right scans forward checking potential profit.
    // TC: O(n) - single pass, right moves forward once through the array
    // SC: O(1) - only pointers and profit variable used
    public int maxProfit(int[] prices) {
        int profit = 0;
        int len = prices.length;
        if(len == 1) return 0; // can't trade with a single day

        int left = 0, right = left+1; // left = min price index, right = scanner

        while(left < right && right < len){
            if(prices[left] > prices[right]){
                // found a new lower price, move left (min) to this position
                left = right;
                right++;
            }else{
                // update max profit using current min and current price
                profit = Math.max(profit, prices[right] - prices[left]);
                right++;
            }
        }
        return profit;
    }
}