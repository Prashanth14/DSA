class Solution {
    // Approach: track seen digits per row, per column, and per 3x3 box
    // using HashSets. Scan every cell once; if a digit is already in
    // that cell's row/col/box set, it's a duplicate -> invalid board.
    //
    // TC: O(1) -> board is always a fixed 9x9 = 81 cells, so this is
    //     technically constant work (commonly written as O(81))
    // SC: O(1) -> 27 fixed HashSets (9 rows + 9 cols + 9 boxes), each
    //     holding at most 9 characters - bounded regardless of input
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') continue; // empty cell, nothing to check

                // Row check: has this digit appeared in row i before?
                if (rows[i].contains(ch)) return false;
                rows[i].add(ch);

                // Column check: has this digit appeared in column j before?
                if (cols[j].contains(ch)) return false;
                cols[j].add(ch);

                // Box check: has this digit appeared in this 3x3 box before?
                //
                // The board is divided into a 3x3 GRID of boxes (9 total),
                // numbered 0-8 left-to-right, top-to-bottom:
                //   box0 box1 box2
                //   box3 box4 box5
                //   box6 box7 box8
                //
                // i/3 tells us which "box row" this cell belongs to (0,1,2),
                // since rows 0-2 -> box row 0, rows 3-5 -> box row 1, etc.
                // j/3 tells us which "box column" this cell belongs to (0,1,2),
                // same idea across columns.
                //
                // (i/3)*3 converts that box-row into the correct starting
                // offset in a flattened 0-8 index (row-major order, same
                // trick as converting any 2D (row,col) grid position into
                // a 1D array index). Adding j/3 then picks the right box
                // within that row of boxes.
                //
                // Example: cell (4,7) -> i/3 = 1 (box row 1), j/3 = 2
                // (box col 2) -> boxIndex = 1*3 + 2 = 5 (box5, which spans
                // rows 3-5 and columns 6-8 - matches cell (4,7)).
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (boxes[boxIndex].contains(ch)) return false;
                boxes[boxIndex].add(ch);
            }
        }
        return true;
    }
}