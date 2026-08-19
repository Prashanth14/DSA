class Solution {

    /*
     * Pattern: Binary Search on a 2D Matrix
     *
     * Key observation:
     *
     * Each row is sorted AND
     * the first element of every row is greater than
     * the last element of the previous row.
     *
     * Example:
     *
     * matrix =
     * [
     *   [1,  2,  4,  8],
     *   [10, 11, 12, 13],
     *   [14, 20, 30, 40]
     * ]
     *
     * Because of the given properties, we can imagine this
     * matrix as ONE sorted 1D array:
     *
     * [1, 2, 4, 8, 10, 11, 12, 13, 14, 20, 30, 40]
     *
     * Therefore, instead of doing separate binary searches,
     * we can perform ONE binary search over:
     *
     *      0 ... (rows * cols - 1)
     *
     *
     * ---------------------------------------------------------
     * MOST IMPORTANT INTUITION: Converting mid -> row and column
     * ---------------------------------------------------------
     *
     * Suppose cols = 4.
     *
     * Matrix positions correspond to flattened indices:
     *
     * 1D index:     0  1  2  3
     * matrix:      [1, 2, 4, 8]       row 0
     *
     * 1D index:     4   5   6   7
     * matrix:      [10, 11, 12, 13]   row 1
     *
     * 1D index:     8   9  10  11
     * matrix:      [14, 20, 30, 40]   row 2
     *
     *
     * Every row contains exactly `cols` elements.
     *
     * Therefore:
     *
     *      row = mid / cols
     *
     * Integer division tells us how many COMPLETE rows
     * we have passed.
     *
     * Example:
     *
     * mid = 6
     * cols = 4
     *
     * row = 6 / 4 = 1
     *
     * So flattened index 6 belongs to row 1.
     *
     *
     * To find the column:
     *
     *      col = mid % cols
     *
     * The remainder tells us the position INSIDE that row.
     *
     * mid = 6
     * cols = 4
     *
     * col = 6 % 4 = 2
     *
     * Therefore:
     *
     * flattened index 6
     *      ↓
     * matrix[1][2]
     *      ↓
     * 12
     *
     *
     * Easy way to remember:
     *
     *      row = index / numberOfColumns
     *      col = index % numberOfColumns
     *
     *      "/" tells us WHICH ROW
     *      "%" tells us WHERE INSIDE THE ROW
     *
     *
     * Time Complexity:
     *
     * There are rows * cols total elements.
     *
     * Binary search eliminates half of the remaining
     * search space every iteration.
     *
     * TC = O(log(rows * cols))
     *
     *
     * Space Complexity:
     *
     * We do NOT actually create a flattened array.
     * We only mathematically treat the matrix as flattened.
     *
     * Only a few integer variables are used.
     *
     * SC = O(1)
     */

    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        /*
         * Treat the matrix as a virtual 1D sorted array.
         *
         * Total elements = rows * cols
         *
         * So valid flattened indices are:
         *
         * 0 ... rows * cols - 1
         */
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {

            // Standard overflow-safe binary search mid.
            int mid = left + (right - left) / 2;

            /*
             * Convert the virtual 1D index into
             * actual matrix coordinates.
             *
             * Example:
             *
             * cols = 4
             * mid  = 6
             *
             * r = 6 / 4 = 1
             * c = 6 % 4 = 2
             *
             * So check matrix[1][2].
             */
            int r = mid / cols;
            int c = mid % cols;

            if (matrix[r][c] == target) {

                // Target found.
                return true;

            } else if (target > matrix[r][c]) {

                /*
                 * Current value is too small.
                 *
                 * Since the virtual array is sorted,
                 * target must be somewhere to the RIGHT.
                 */
                left = mid + 1;

            } else {

                /*
                 * Current value is too large.
                 *
                 * Target, if present, must be to the LEFT.
                 */
                right = mid - 1;
            }
        }

        // Search space exhausted -> target doesn't exist.
        return false;
    }
}