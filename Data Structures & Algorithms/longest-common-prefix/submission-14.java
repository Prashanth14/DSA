class Solution {
    // Approach: vertical scan with StringBuilder. Check character
    // position by position across ALL strings at the same index.
    // Stop at first mismatch or first string that runs out of chars.
    // StringBuilder avoids the repeated String allocation cost of "+=".
    // TC: O(n * m) -> n = number of strings, m = length of final prefix
    // SC: O(1) extra (not counting output)
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        int sLen = strs[0].length(); // reference length from first string
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < sLen; i++) {
            char ch = strs[0].charAt(i); // character to check at this position

            for (int j = 1; j < len; j++) {
                // strs[j] too short to have index i -> prefix ends here
                if (i >= strs[j].length()) return prefix.toString();

                // character mismatch -> prefix ends here
                if (strs[j].charAt(i) != ch) {
                    return prefix.toString();
                }
            }

            prefix.append(ch); // matched in all strings -> keep this character
        }

        return prefix.toString(); // strs[0] fully matched -> it's the whole prefix
    }
}