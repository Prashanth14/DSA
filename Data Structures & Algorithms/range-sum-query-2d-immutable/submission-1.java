class NumMatrix {
    private int[][] matrix; // stores reference to input matrix, no precomputation

    // TC: O(1) -> just stores the reference, no processing done upfront
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    // Approach: brute force. Sum every cell in the requested rectangle
    // directly, for every single call.
    // TC: O(rows * cols) worst case per call -> re-sums the region from
    //     scratch each time, no reuse of previous work
    // SC: O(1) extra (not counting the stored matrix reference)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                sum += matrix[r][c];
            }
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */