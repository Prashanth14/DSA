class Solution {

    /*
     * Pattern: Binary Search on Answer
     *
     * Goal:
     * Find the MINIMUM eating speed k such that Koko can finish
     * all piles within <= h hours.
     *
     *
     * KEY OBSERVATION:
     *
     * We are not binary-searching an element in the array.
     * We are binary-searching the possible ANSWER (eating speed).
     *
     * Minimum possible speed = 1 banana/hour
     *
     * Maximum necessary speed = max(piles)
     *
     * Why max(piles)?
     * If k = maxPile, every pile can be finished in at most 1 hour.
     * Since h >= piles.length, maxPile is always a valid upper bound.
     *
     *
     * ---------------------------------------------------------
     * FEASIBILITY CHECK: Does a candidate speed k work?
     * ---------------------------------------------------------
     *
     * Each pile must be handled separately because Koko cannot
     * start another pile during the same hour.
     *
     * Example:
     *
     * pile = 7
     * k = 3
     *
     * Hour 1 -> eat 3
     * Hour 2 -> eat 3
     * Hour 3 -> eat 1
     *
     * So this pile requires:
     *
     * ceil(7 / 3) = 3 hours
     *
     * Therefore:
     *
     * totalHours =
     *      ceil(pile1 / k)
     *    + ceil(pile2 / k)
     *    + ...
     *
     *
     * IMPORTANT:
     *
     * Do NOT calculate:
     *
     *      totalBananas / k
     *
     * Example:
     *
     * piles = [3,3], k = 2
     *
     * Wrong:
     * total bananas = 6
     * 6 / 2 = 3 hours
     *
     * Actual:
     * ceil(3/2) + ceil(3/2)
     * = 2 + 2
     * = 4 hours
     *
     *
     * ---------------------------------------------------------
     * WHY BINARY SEARCH WORKS
     * ---------------------------------------------------------
     *
     * As eating speed increases, required hours can only decrease.
     *
     * Possible speeds conceptually look like:
     *
     * k:
     * 1   2   3   4   5   6   7 ...
     *
     *     too slow       fast enough
     * F   F   F   T   T   T   T ...
     *             ^
     *         answer = first TRUE
     *
     * Therefore, we are searching for the FIRST valid speed.
     *
     *
     * If hours <= h:
     *
     *      mid WORKS.
     *
     * But there may be a smaller speed that also works.
     * So search the LEFT side.
     *
     *      right = mid
     *
     * We keep mid because mid itself could be the minimum answer.
     *
     *
     * If hours > h:
     *
     *      mid is TOO SLOW.
     *
     * mid cannot be the answer, and neither can any smaller speed.
     *
     *      left = mid + 1
     *
     *
     * Eventually:
     *
     *      left == right
     *
     * and that position is the FIRST valid eating speed.
     *
     * Therefore return left.
     *
     *
     * ---------------------------------------------------------
     * COMPLEXITY
     * ---------------------------------------------------------
     *
     * Let:
     * n = piles.length
     * M = max(piles)
     *
     * Binary search over speeds 1...M:
     *      O(log M)
     *
     * For every candidate speed, scan all n piles:
     *      O(n)
     *
     * TC: O(n * log M)
     *
     * We only use a few variables.
     *
     * SC: O(1)
     */

    public int minEatingSpeed(int[] piles, int h) {

        /*
         * Find the largest pile.
         *
         * Search space for eating speed:
         *
         * 1 ... maxPile
         */
        int maxPile = Integer.MIN_VALUE;

        for (int pile : piles) {
            if (pile > maxPile) {
                maxPile = pile;
            }
        }


        // Minimum possible eating speed.
        int left = 1;

        // Maximum eating speed we ever need to consider.
        int right = maxPile;


        /*
         * Binary search for the FIRST speed that allows
         * Koko to finish within h hours.
         */
        while (left < right) {

            /*
             * Overflow-safe mid calculation.
             *
             * mid represents the candidate eating speed k.
             */
            int mid = left + (right - left) / 2;

            // Total hours needed if Koko eats mid bananas/hour.
            long hours = 0;


            /*
             * Calculate required hours pile-by-pile.
             *
             * Math.ceil is necessary because even a partially
             * eaten final chunk requires a complete hour.
             *
             * Example:
             *
             * pile = 7, mid = 3
             *
             * ceil(7 / 3) = 3 hours.
             */
            for (int pile : piles) {
                hours += (long) Math.ceil((double) pile / mid);
            }


            if (hours <= h) {

                /*
                 * mid is fast enough.
                 *
                 * But we need the MINIMUM valid speed,
                 * so search for a potentially smaller answer.
                 *
                 * Do NOT use mid - 1 because mid itself
                 * could be the minimum valid answer.
                 */
                right = mid;

            } else {

                /*
                 * mid is too slow.
                 *
                 * We definitely need a faster eating speed,
                 * so discard mid and everything below it.
                 */
                left = mid + 1;
            }
        }


        /*
         * Binary search stops when:
         *
         * left == right
         *
         * This is the smallest eating speed for which
         * hours <= h.
         */
        return left;
    }
}