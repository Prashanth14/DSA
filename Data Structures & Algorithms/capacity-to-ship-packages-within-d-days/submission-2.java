class Solution {

    /*
     * Pattern: Binary Search on Answer
     *
     * Goal:
     * Find the MINIMUM ship capacity that allows us to ship
     * all packages within <= given "days".
     *
     * IMPORTANT:
     * Packages must be shipped in the SAME ORDER as given.
     *
     *
     * ---------------------------------------------------------
     * 1. WHAT ARE WE BINARY SEARCHING?
     * ---------------------------------------------------------
     *
     * We are NOT searching for an element inside weights[].
     *
     * We are binary searching the possible SHIP CAPACITY.
     *
     *
     * ---------------------------------------------------------
     * 2. SEARCH SPACE
     * ---------------------------------------------------------
     *
     * Minimum possible capacity = max(weights)
     *
     * Why?
     * The ship must at least be able to carry the heaviest
     * individual package because packages cannot be split.
     *
     * Example:
     *
     * weights = [2,4,6,1,3,10]
     *
     * maxWeight = 10
     *
     * A capacity < 10 can NEVER work because the package
     * weighing 10 could never be shipped.
     *
     *
     * Maximum possible capacity = sum(weights)
     *
     * Why?
     * If the ship capacity equals the total weight,
     * we can ship ALL packages in one day.
     *
     * Therefore:
     *
     *      left  = max(weights)
     *      right = sum(weights)
     *
     *
     * ---------------------------------------------------------
     * 3. FEASIBILITY CHECK
     * ---------------------------------------------------------
     *
     * For every candidate capacity "mid", simulate shipping
     * packages in their given order.
     *
     * Keep:
     *
     *      currentLoad  = weight loaded for current day
     *      requiredDays = number of days needed
     *
     * Start with:
     *
     *      requiredDays = 1
     *
     * because we start loading packages on Day 1.
     *
     *
     * For each package:
     *
     * If:
     *
     *      currentLoad + weight <= capacity
     *
     * add the package to the CURRENT day.
     *
     * Otherwise:
     *
     *      requiredDays++
     *
     * Start a NEW day and make the current package the
     * first package of that new day:
     *
     *      currentLoad = weight
     *
     *
     * ---------------------------------------------------------
     * EXAMPLE
     * ---------------------------------------------------------
     *
     * weights = [2,4,6,1,3,10]
     * capacity = 10
     *
     * Day 1:
     *
     * 2          -> load = 2
     * 2 + 4 = 6  -> load = 6
     *
     * Next package = 6
     *
     * 6 + 6 = 12 > 10
     *
     * Start Day 2:
     *
     * load = 6
     * 6 + 1 = 7
     * 7 + 3 = 10
     *
     * Next package = 10
     *
     * 10 + 10 > 10
     *
     * Start Day 3:
     *
     * load = 10
     *
     * Therefore:
     *
     * requiredDays = 3
     *
     *
     * ---------------------------------------------------------
     * 4. WHY BINARY SEARCH WORKS
     * ---------------------------------------------------------
     *
     * As ship capacity increases, requiredDays can only
     * decrease or remain the same.
     *
     * Conceptually:
     *
     * capacity:
     *
     * small -------------------------------> large
     *
     *   DOESN'T WORK        |        WORKS
     *
     *   F   F   F   F       T   T   T   T
     *                       ^
     *                 minimum capacity
     *
     * We are searching for the FIRST TRUE / minimum
     * capacity that works.
     *
     *
     * If requiredDays <= days:
     *
     *      mid capacity WORKS.
     *
     * But maybe a smaller capacity also works.
     *
     * Therefore:
     *
     *      right = mid
     *
     * IMPORTANT:
     * We do NOT use mid - 1 because mid itself could
     * be the minimum valid capacity.
     *
     *
     * If requiredDays > days:
     *
     *      mid capacity is TOO SMALL.
     *
     * We need a larger ship capacity.
     *
     * Therefore:
     *
     *      left = mid + 1
     *
     *
     * When binary search finishes:
     *
     *      left == right
     *
     * and both point to the minimum valid capacity.
     *
     * Therefore return left.
     *
     *
     * ---------------------------------------------------------
     * TIME & SPACE COMPLEXITY
     * ---------------------------------------------------------
     *
     * Let:
     *
     * n = weights.length
     * S = sum(weights)
     *
     * Finding max and sum:
     *      O(n)
     *
     * Binary search over possible capacities:
     *      O(log S)
     *
     * For every candidate capacity, we scan all packages:
     *      O(n)
     *
     * Overall:
     *
     *      TC: O(n * log S)
     *
     * More precisely, the search range is
     * [maxWeight, sumWeight], so it can also be written as:
     *
     *      O(n * log(sumWeight - maxWeight + 1))
     *
     * We use only a few integer variables:
     *
     *      SC: O(1)
     */

    public int shipWithinDays(int[] weights, int days) {

        int max = 0;
        int sum = 0;

        /*
         * Find:
         *
         * max = minimum possible ship capacity
         * sum = maximum possible ship capacity
         */
        for (int w : weights) {
            max = Math.max(w, max);
            sum += w;
        }

        /*
         * Binary search over possible ship capacities.
         *
         * left  = smallest possible capacity
         * right = largest capacity we would ever need
         */
        int left = max;
        int right = sum;


        while (left < right) {

            // Candidate ship capacity.
            int mid = left + (right - left) / 2;

            /*
             * Simulate how many days are required
             * if the ship capacity is exactly "mid".
             *
             * We start on Day 1.
             */
            int requiredDays = 1;

            // Total weight loaded onto the ship for the current day.
            int currentLoad = 0;


            for (int w : weights) {

                /*
                 * If this package still fits within today's
                 * ship capacity, load it today.
                 */
                if (currentLoad + w <= mid) {

                    currentLoad += w;

                } else {

                    /*
                     * Package does NOT fit today.
                     *
                     * Start a new shipping day.
                     */
                    requiredDays++;

                    /*
                     * IMPORTANT:
                     * The current package becomes the FIRST
                     * package loaded on the new day.
                     */
                    currentLoad = w;
                }
            }


            if (requiredDays <= days) {

                /*
                 * mid capacity works.
                 *
                 * Since we need the MINIMUM capacity,
                 * try a smaller capacity.
                 *
                 * Keep mid because it could itself be
                 * the minimum valid answer.
                 */
                right = mid;

            } else {

                /*
                 * mid capacity is too small.
                 *
                 * We need more capacity, so discard mid
                 * and everything smaller than mid.
                 */
                left = mid + 1;
            }
        }


        /*
         * Binary search converges when:
         *
         *      left == right
         *
         * This is the minimum capacity that can ship
         * everything within the given number of days.
         */
        return left;
    }
}