class Solution {
    /*
     * Pattern: Stack + Path Parsing
     *
     * Key idea:
     * Each directory between two '/' characters is treated as one token.
     *
     * For every token:
     *   ""   -> ignore (caused by multiple slashes like // or ///)
     *   "."  -> ignore (stay in current directory)
     *   ".." -> go to parent directory, so pop from stack if possible
     *   else -> valid directory/file name, push onto stack
     *
     * Important:
     * "..." and "...." are NORMAL directory names.
     * Only "." and ".." have special meaning.
     *
     * Example:
     * path = "/a//./b/../../c/.../"
     *
     * Tokens:
     * a   -> push      [a]
     * ""  -> ignore
     * .   -> ignore
     * b   -> push      [a, b]
     * ..  -> pop       [a]
     * ..  -> pop       []
     * c   -> push      [c]
     * ... -> push      [c, ...]
     *
     * Result = "/c/..."
     *
     * TC: O(n)
     * - We scan the path once.
     * - Building the final result also processes at most O(n) characters.
     *
     * SC: O(n)
     * - Stack can store up to O(n) characters/directories.
     * - dirName and result builders can also use O(n) space.
     *
     * n = length of the input path.
     */

    public String simplifyPath(String path) {

        // Stores only valid directories in canonical order.
        // ".." removes the most recently added directory.
        Stack<String> st = new Stack<>();

        // Builds one complete directory/token between '/' characters.
        StringBuilder dirName = new StringBuilder();

        /*
         * Add an extra '/' so the final token is processed inside the loop.
         *
         * Example:
         * "/home/user" -> "/home/user/"
         *
         * Without this, "user" would remain in dirName after the loop
         * and would need separate processing.
         */
        path = path + "/";

        for (char ch : path.toCharArray()) {

            if (ch != '/') {

                // Keep building the current token.
                // This may include letters, digits, '_', or '.'.
                dirName.append(ch);

            } else {

                // '/' means the current token is complete.
                String dir = dirName.toString();

                if (dir.equals("..")) {

                    // Move to parent directory.
                    // If already at root, there is nothing to remove.
                    if (!st.isEmpty()) {
                        st.pop();
                    }

                } else if (!dir.equals(".") && !dir.equals("")) {

                    /*
                     * Any token other than "", ".", and ".."
                     * is a valid directory name.
                     *
                     * Examples:
                     * "home", "_user", "abc123", "...", "a.b"
                     */
                    st.push(dir);
                }

                // Clear the builder for the next directory token.
                dirName.setLength(0);
            }
        }

        // If no valid directories remain, canonical path is root.
        if (st.isEmpty()) {
            return "/";
        }

        StringBuilder res = new StringBuilder();

        /*
         * Java Stack iteration goes from index 0 to the last element,
         * which gives us the directories in the correct path order.
         *
         * Example stack:
         * [home, user, docs]
         *
         * Iteration produces:
         * home -> user -> docs
         *
         * Result:
         * /home/user/docs
         */
        for (String dir : st) {
            res.append("/").append(dir);
        }

        return res.toString();
    }
}