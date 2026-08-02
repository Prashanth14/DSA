class NumMatrix {
    private int[][] prefix; // prefix[i][j] = sum of rectangle from (0,0) to (i,j)
    private int[][] matrix;

    // TC: O(rows * cols) -> one pass to build the whole prefix table
    // SC: O(rows * cols) -> prefix table, same size as input matrix
    public NumMatrix(int[][] matrix) {
        // Defensive check: avoid crashing on null/empty input.
        // (Note: doesn't fully protect sumRegion() if this triggers,
        // since prefix stays uninitialized - just avoids a crash here.)
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        this.matrix = matrix;

        int n = matrix.length;
        int m = matrix[0].length;

        prefix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Sum from above, sum from left, minus double-counted
                // top-left overlap, plus the current cell itself.
                int top = (i > 0) ? prefix[i - 1][j] : 0;
                int left = (j > 0) ? prefix[i][j - 1] : 0;
                int topLeft = (i > 0 && j > 0) ? prefix[i - 1][j - 1] : 0;

                prefix[i][j] = matrix[i][j] + top + left - topLeft;
            }
        }
    }

    // Approach: inclusion-exclusion on the precomputed prefix table.
    // Take everything up to (row2,col2), remove everything above row1
    // and everything left of col1, add back the corner removed twice.
    // TC: O(1) -> just 4 lookups + arithmetic, no loops
    // SC: O(1) -> no extra space used per call
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = prefix[row2][col2];
        int top = (row1 > 0) ? prefix[row1 - 1][col2] : 0;
        int left = (col1 > 0) ? prefix[row2][col1 - 1] : 0;
        int topLeft = (row1 > 0 && col1 > 0) ? prefix[row1 - 1][col1 - 1] : 0;

        return total - top - left + topLeft;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */