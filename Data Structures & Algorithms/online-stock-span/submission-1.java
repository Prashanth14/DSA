class StockSpanner {
    // Monotonic DECREASING stack (by price), storing {price, span} pairs.
    // Each entry represents a "block" of consecutive past days that are
    // already known to be <= some earlier price, with span = how many
    // consecutive days (including itself) that block already covers.
    Stack<int[]> st;

    public StockSpanner() {
        st = new Stack<>();
    }

    public int next(int price) {
        // Every price has a span of at least 1 (itself).
        int span = 1;

        // While today's price is >= the price on top of the stack,
        // today "absorbs" that entire past block — everything that
        // block already covered is automatically <= today's price too
        // (since it was <= that block's price, which is <= today's price).
        // So fold its span into today's running total and discard it.
        while (!st.isEmpty() && st.peek()[0] <= price) {
            span += st.pop()[1];
        }

        // Push today as a new block: {price, total span so far}.
        // Future days will compare against THIS price/span pair,
        // not the individual days we just absorbed — that's what
        // keeps each call efficient (no need to re-walk absorbed days).
        st.push(new int[]{price, span});

        return span;
    }
}

/**
 * Key idea to remember on revisit:
 * Instead of tracking individual days, the stack tracks "spans already
 * resolved" as single collapsed units. When a new price is >= a unit's
 * price, that whole unit (and everything inside it) is guaranteed <=
 * the new price, so it gets merged in one O(1) step per unit rather
 * than re-checking every day inside it individually. This merging is
 * exactly why the total cost across all calls stays O(n) instead of
 * degrading toward the O(n^2) brute-force approach (scan backward
 * day-by-day for every call).
 *
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */