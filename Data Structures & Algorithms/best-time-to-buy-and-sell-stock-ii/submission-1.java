class Solution {
    // Approach: greedy, single pass. Capture every profitable day-to-day
    // increase (since multiple transactions are allowed, summing all
    // small gains equals the same total as any larger combined trade,
    // while also avoiding losses from holding through dips).
    // TC: O(n) -> single pass through prices
    // SC: O(1) -> only a couple of variables, no extra array/map
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int max = 0;
        int start = prices[0]; // baseline price to compare against

        for (int i = 1; i < len; i++) {
            if (start < prices[i]) {
                max += prices[i] - start; // capture this day-to-day gain
            }
            start = prices[i]; // always move baseline forward, win or lose
        }

        return max;
    }
}