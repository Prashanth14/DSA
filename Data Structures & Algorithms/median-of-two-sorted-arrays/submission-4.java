class Solution {

    /*
     * Pattern: Binary Search on Partition
     *
     * Goal:
     * Find the median of two SORTED arrays without actually merging them.
     *
     * Main Idea:
     * We partition both arrays such that:
     *
     *      LEFT HALF | RIGHT HALF
     *
     * 1. Left half contains the required number of elements.
     * 2. Every element in the left half <= every element in the right half.
     *
     *
     * Example:
     *
     * nums1 = [1, 3]
     * nums2 = [2, 4]
     *
     * A correct partition can be:
     *
     * nums1: [1] | [3]
     * nums2: [2] | [4]
     *
     * Combined:
     *
     * LEFT  = [1,2]
     * RIGHT = [3,4]
     *
     * We don't actually create these combined halves.
     * We only inspect the values around the partitions.
     *
     *
     * TC: O(log(min(m,n)))
     * SC: O(1)
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n1Len = nums1.length;
        int n2Len = nums2.length;

        /*
         * Always binary search on the SMALLER array.
         *
         * This:
         * 1. Gives O(log(min(m,n))) time.
         * 2. Makes partitionY stay within valid boundaries.
         */
        int[] smaller = n1Len > n2Len ? nums2 : nums1;
        int[] larger  = n1Len > n2Len ? nums1 : nums2;

        int totalLength = n1Len + n2Len;

        /*
         * partitionX represents NUMBER OF ELEMENTS
         * taken from smaller into the LEFT half.
         *
         * Possible values:
         *
         * 0 ... smaller.length
         *
         * IMPORTANT:
         * high = smaller.length, NOT smaller.length - 1,
         * because partition can come AFTER the last element.
         */
        int low = 0;
        int high = smaller.length;

        while (low <= high) {

            // Choose partition in the smaller array.
            int partitionX = low + (high - low) / 2;

            /*
             * Total elements required in LEFT half:
             *
             *      (totalLength + 1) / 2
             *
             * If partitionX elements come from smaller,
             * the remaining must come from larger.
             *
             * partitionX + partitionY
             *      = (totalLength + 1) / 2
             */
            int partitionY =
                    (totalLength + 1) / 2 - partitionX;


            /*
             * Values immediately around both partitions:
             *
             * smaller:
             *
             *     ... l1 | r1 ...
             *            ^
             *        partitionX
             *
             * larger:
             *
             *     ... l2 | r2 ...
             *            ^
             *        partitionY
             *
             *
             * l1 = largest element from smaller on LEFT
             * r1 = smallest element from smaller on RIGHT
             *
             * l2 = largest element from larger on LEFT
             * r2 = smallest element from larger on RIGHT
             */


            /*
             * If partitionX == 0:
             * smaller contributes nothing to LEFT.
             *
             * Treat missing left value as -infinity.
             */
            int l1 = partitionX == 0
                    ? Integer.MIN_VALUE
                    : smaller[partitionX - 1];


            /*
             * If partitionX == smaller.length:
             * smaller contributes nothing to RIGHT.
             *
             * Treat missing right value as +infinity.
             */
            int r1 = partitionX == smaller.length
                    ? Integer.MAX_VALUE
                    : smaller[partitionX];


            // Same boundary handling for larger array.
            int l2 = partitionY == 0
                    ? Integer.MIN_VALUE
                    : larger[partitionY - 1];

            int r2 = partitionY == larger.length
                    ? Integer.MAX_VALUE
                    : larger[partitionY];


            /*
             * CORRECT PARTITION CONDITION:
             *
             * Every LEFT element must be <= every RIGHT element.
             *
             * Since both arrays are individually sorted,
             * checking the four boundary values is enough:
             *
             *      l1 <= r2
             *      l2 <= r1
             *
             *
             * smaller: ... l1 | r1 ...
             * larger : ... l2 | r2 ...
             *
             *               ↑
             *        correct partition
             */
            if (l1 <= r2 && l2 <= r1) {

                /*
                 * EVEN total length:
                 *
                 * Example:
                 *
                 * [1,2 | 3,4]
                 *
                 * Median =
                 *
                 * largest LEFT + smallest RIGHT
                 * --------------------------------
                 *                 2
                 *
                 * largest LEFT  = max(l1,l2)
                 * smallest RIGHT = min(r1,r2)
                 */
                if (totalLength % 2 == 0) {

                    return ((double) Math.max(l1, l2)
                            + Math.min(r1, r2)) / 2.0;

                } else {

                    /*
                     * ODD total length:
                     *
                     * Because we used:
                     *
                     *      (totalLength + 1) / 2
                     *
                     * LEFT contains one extra element.
                     *
                     * Therefore median is simply:
                     *
                     *      largest element on LEFT
                     */
                    return Math.max(l1, l2);
                }
            }


            /*
             * l1 > r2
             *
             * Example:
             *
             * smaller: ... 8 | ...
             * larger : ...   | 5 ...
             *
             * 8 > 5
             *
             * We took TOO MANY elements from smaller
             * into the LEFT half.
             *
             * Move partitionX LEFT.
             */
            if (l1 > r2) {

                high = partitionX - 1;

            } else {

                /*
                 * Otherwise:
                 *
                 * l2 > r1
                 *
                 * Example:
                 *
                 * smaller: ... | 3 ...
                 * larger : ... 7 | ...
                 *
                 * 7 > 3
                 *
                 * We took TOO FEW elements from smaller
                 * into the LEFT half.
                 *
                 * Move partitionX RIGHT.
                 */
                low = partitionX + 1;
            }
        }

        /*
         * Problem guarantees valid sorted input,
         * so normally this line is never reached.
         */
        return 0;
    }
}