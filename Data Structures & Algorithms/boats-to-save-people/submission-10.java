/**
 *
 * Approach: Counting Sort + Greedy Two Pointers
 *
 * ---------------------------------------------------------------------
 * CORE INSIGHT (the greedy)
 * ---------------------------------------------------------------------
 * Once the array is sorted, walk from both ends:
 *   - The HEAVIEST remaining person always needs a boat. No way around it.
 *   - The only question is whether the LIGHTEST remaining person can ride
 *     along. If the lightest cannot fit beside the heaviest, then NOBODY
 *     can, so the heaviest sails alone.
 * So every iteration launches exactly one boat and removes 1 or 2 people.
 *
 * Why it is optimal (exchange argument):
 *   Pairing the heaviest with the lightest never loses. If some optimal
 *   solution pairs the heaviest H with someone else X (or leaves H alone)
 *   while the lightest L rides with Y, we can swap to (H,L) and (X,Y):
 *   since L <= X, the pair (H,L) still fits, and (X,Y) fits because
 *   X + Y <= H + Y <= limit. Boat count never increases -> greedy is safe.
 *
 * ---------------------------------------------------------------------
 * WHY COUNTING SORT INSTEAD OF Arrays.sort
 * ---------------------------------------------------------------------
 * Weights are bounded: 1 <= people[i] <= limit <= 30,000.
 * Small bounded integer range -> counting sort runs in O(n + m) instead
 * of O(n log n). With n = 50,000: n log n ~ 800,000 ops vs n + m ~ 80,000.
 * The trade is O(m) extra space for the frequency table.
 *
 * ---------------------------------------------------------------------
 * COMPLEXITY   (n = people.length, m = max weight <= limit)
 * ---------------------------------------------------------------------
 * Time  : O(n + m)
 *         - max() scan .............. O(n)
 *         - build freq table ........ O(n)
 *         - rebuild sorted array .... O(n + m)   see note at that loop
 *         - two-pointer boat loop ... O(n)
 *         Optimal: reading all n weights is an unavoidable Omega(n).
 *
 * Space : O(m) for the freq array. The sort is in place on `people`
 *         and the boat loop uses only scalars.
 *         (Arrays.sort would be O(log n) space but O(n log n) time -
 *          a genuine trade-off, not a strict win either way.)
 *
 * NOTE: this method MUTATES the caller's array - `people` comes back sorted.
 */
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int len = people.length;

        // ============================================================
        // PHASE 1 - Counting sort, part A: tally each weight
        // ============================================================

        // Size the table by the actual max weight, not by `limit`.
        // Tighter bound = smaller array when everyone happens to be light.
        int maxWeightPerson = Arrays.stream(people).max().getAsInt();

        // freq[w] = how many people weigh exactly w
        int[] freq = new int[maxWeightPerson + 1];
        for (int p : people) {
            freq[p] += 1;
        }

        // ============================================================
        // PHASE 2 - Counting sort, part B: rebuild `people` ascending
        // ============================================================
        // idx = WRITE pointer into people[]  (0 -> len-1, never rewinds)
        // i   = READ  pointer over freq[]    (1 -> m,     never rewinds)
        //
        // Each pass: skip empty buckets, emit one person of weight i,
        // decrement that bucket. Because i only ever increases, the values
        // are written in non-decreasing order - no comparisons needed.
        //
        // Duplicates: i STALLS on the same weight while freq[i] > 0, so a
        // weight with count 3 is emitted three times before i moves on.
        //
        // Why this nested loop is O(n + m), not O(n * m):
        //   `i` is declared OUTSIDE the outer loop and is never reset, so
        //   across the entire run it advances at most m times TOTAL, while
        //   idx advances exactly n times. Total work = O(n + m).
        //
        // CAVEAT: i starts at 1 because the constraints guarantee
        // people[i] >= 1. If a weight of 0 were possible, the inner while
        // would walk past the end of freq and throw. Starting at i = 0
        // costs nothing and would make this robust.
        int idx = 0, i = 1;
        while (idx < len) {
            while (freq[i] == 0) {   // no one left at this weight, move up
                i++;
            }
            people[idx++] = i;       // place one person of weight i
            freq[i]--;               // mark that person as placed
        }
        // people[] is now sorted ascending.

        // ============================================================
        // PHASE 3 - Greedy two pointers: assign boats
        // ============================================================
        // left  -> lightest person not yet boarded
        // right -> heaviest person not yet boarded
        int left = 0, right = len - 1;
        int minBoatsToRescue = 0;

        while (left <= right) {
            // The heaviest person boards unconditionally; a boat is always
            // launched. `remain` is the leftover capacity beside them.
            int remain = limit - people[right];
            minBoatsToRescue++;
            right--;

            // Can the lightest person share this boat?
            // The `left <= right` guard is NOT optional: when only one
            // person remains, left and right point at the SAME individual,
            // and without the guard we would board them twice and
            // undercount the boats.
            if (left <= right && people[left] <= remain) {
                left++;
            }
            // If they do not fit, `left` stays put - the heaviest sailed
            // alone, and we retry the same lightest person next round.
        }

        return minBoatsToRescue;
    }
}