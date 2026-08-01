class Solution {
    // Approach: horizontal scan. Start with first string as the prefix,
    // then shrink it down using startsWith() until every string matches.
    // TC: O(n * m) worst case -> n = number of strings, m = length of
    //     first string (each startsWith call + substring costs O(m))
    // SC: O(1) extra (not counting output) -- though substring() creates
    //     a new String object on every shrink, which has real memory cost
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        String prefix = strs[0]; // start by assuming first string is the whole prefix

        for (int i = 1; i < len; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // shrink by 1 char
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}

// substring() creates a brand new String object on every single shrink — real memory churn, not just a conceptual cost.

// startsWith(prefix) re-checks the prefix from index 0 every time, meaning it re-compares characters you already confirmed matched 
// in a previous call — wasted repeated work.