class Solution {
    // Approach: vertical scan. Check character position by position,
    // comparing ALL strings at that same index. Stop at the first
    // mismatch or the first string that runs out of characters.
    // TC: O(n * m) -> n = number of strings, m = length of final prefix
    //     (stops at first mismatch, so no wasted comparisons)
    // SC: O(1) extra (not counting output)
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        int sLen = strs[0].length(); // use first string as the reference length
        String prefix = "";

        for (int i = 0; i < sLen; i++) {
            char ch = strs[0].charAt(i); // character to check at this position

            for (int j = 1; j < len; j++) {
                // strs[j] too short to have index i -> prefix ends here
                // character mismatch -> prefix ends here
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return prefix;
                }
            }

            prefix += ch; // this character matched in all strings -> keep it
        }

        return prefix; // strs[0] fully matched everything -> it's the whole prefix
    }
}


// Suggestions:
// Your approach is already optimal for the average case. Using a StringBuilder instead of string concatenation would further reduce the constant factor overhead.