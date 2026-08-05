class Solution {
    // Pattern: Sliding window (variable size) with HashSet
    // TC: O(n) - each character is added and removed from the set at most once
    // SC: O(min(n, charset size)) - set holds at most one window's worth of unique chars
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();

        Set<Character> set = new HashSet<>(); // characters currently in the window

        int left = 0;      // left edge of window
        int longest = 0;

        for(int right = 0; right < len; right++){
            char ch = s.charAt(right);

            // shrink window from left until duplicate of ch is fully evicted
            while(set.contains(ch)){
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch); // ch is now safe to add, window has no duplicates
            longest = Math.max(right - left + 1, longest); // update max window size
        }
        return longest;
    }
}