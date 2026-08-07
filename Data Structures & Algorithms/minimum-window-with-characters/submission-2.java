class Solution {
    // Pattern: Variable-size sliding window with frequency maps
    // TC: O((n+m) * 26) ~ O(n+m) - n=s.length(), m=t.length(); building mapT is O(m),
    //     right and left each move at most n times total, each contains() check
    //     scans a fixed 256-size array (constant, independent of input)
    // SC: O(1) - both frequency arrays are fixed size 256, not proportional to input
    public String minWindow(String s, String t) {
         int sLen = s.length();
         int tLen = t.length();

         // no valid window possible if t is null/longer than s
         if(sLen < tLen || t == null) return "";

         int[] mapS = new int[256]; // char frequency in current window of s
         int[] mapT = new int[256]; // required char frequency from t

         for(char ch : t.toCharArray()){
            mapT[ch]++;
         }

         int left = 0, right = 0;
         int minLen  = Integer.MAX_VALUE; // smallest valid window length found
         int minStart = 0;                // start index of that window

         for(; right < sLen; right++){
            mapS[s.charAt(right)]++; // expand window by including s[right]

            // current window satisfies all of t's character requirements,
            // try to shrink it from the left as much as possible while still valid
            while(contains(mapS, mapT)){
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    minStart = left;
                }
                // remove leftmost char and move left forward, then re-check
                // if window is still valid (loop continues shrinking if so)
                mapS[s.charAt(left++)]--;
            }
         }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // checks if mapS has at least as many of every character as mapT requires
    public boolean contains(int[] mapS, int[] mapT){
        for(int i = 0; i < 256; i++){
            if(mapT[i] > mapS[i]){
                return false;
            }
        }
        return true;
    }
}