class Solution {
    // Optimal sliding window (never shrinks below best size found)
    // TC: O(n) - both left and right pointers each move forward at most n times total
    // SC: O(1) - HashMap holds at most 26 letters
    public int characterReplacement(String s, int k) {
        int len = s.length();
        HashMap<Character, Integer> freq = new HashMap<>(); // char counts within current window
        int maxWindow = 0;  // best (largest) valid window length found so far
        int maxFreq = 0;    // highest frequency of any single char seen so far in a window
        int left = 0;       // left edge of window

        for(int right = 0; right < len; right++){
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            // track the most frequent char count ever seen (may go stale, see below)
            maxFreq = Math.max(maxFreq, freq.get(ch));

            // (window length) - maxFreq = number of chars that would need replacing.
            // If that exceeds k, the window is invalid as-is, so shrink from the left
            // by one step: remove the leaving character's count and move left forward.
            //
            // Note: maxFreq is NOT recalculated here even though the true max frequency
            // of the (now smaller) window might actually be lower. This is intentional:
            // - maxFreq only ever needs to be correct enough to prove a window of size
            //   maxWindow+1 is achievable; it does not need to reflect the exact current window.
            // - The window size can only grow again once a NEW character pushes freq
            //   higher than the old (stale) maxFreq, so the window never shrinks below
            //   the best length already recorded in maxWindow.
            // - This keeps the algorithm O(n): we never need a second pass to
            //   recompute maxFreq after shrinking.
            if((right - left + 1) - maxFreq > k){
                freq.put(s.charAt(left), freq.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }

            // window is valid at this point (size stayed same or grew by 1)
            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return maxWindow;
    }
}