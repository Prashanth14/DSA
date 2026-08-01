class Solution {
    // Approach: vertical scan with index tracking (no string building
    // during the loop). Check character position by position across
    // ALL strings at that index. Track only HOW MANY characters matched
    // (matchedLen) - build the actual result string once, at the end.
    //
    // TC: O(n * m) -> n = number of strings, m = length of final prefix
    //     (stops at first mismatch, no wasted comparisons)
    // SC: O(1) extra during the loop (just an int counter);
    //     the final substring() is O(m) but happens exactly once
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        int sLen = strs[0].length(); // reference length from first string
        int matchedLen = 0;          // count of characters confirmed common so far

        for (int i = 0; i < sLen; i++) {
            char ch = strs[0].charAt(i); // character to check at this position

            for (int j = 1; j < len; j++) {
                // strs[j] too short, OR character mismatch -> prefix ends here
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, matchedLen);
                }
            }

            matchedLen++; // this character matched in all strings -> extend match
        }

        return strs[0].substring(0, matchedLen); // strs[0] fully matched -> whole prefix
    }
}

// Why this is the best solution so far:

// Time already sits at the theoretical floor — O(n · m) — since you can't determine the common prefix without comparing characters up to the point they diverge, and this stops at the very first mismatch found anywhere, doing no wasted work beyond that.

// Space during the actual comparison work is now truly O(1) — just a single int counter, no repeated allocation of any kind while scanning. This beats every earlier version: the raw String += approach paid O(m²) in character copies from repeated immutable string creation; the StringBuilder version paid for m separate append() calls; this version pays for exactly one substring() call, right at the end, when the size of the answer is already known — the minimum possible number of allocations to produce the result.

// So this combines the best of everything built so far: the early-exit efficiency of vertical scanning, plus the leanest possible memory behavior — no wasted comparisons, and no wasted allocations either.