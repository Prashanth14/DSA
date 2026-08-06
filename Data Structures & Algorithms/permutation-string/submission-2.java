class Solution {
    // Pattern: Fixed-size sliding window (size = s1.length()) + frequency comparison
    // TC: O(n + m) - n = s2.length(), m = s1.length(); building s1Chars is O(m),
    //     sliding window over s2 is O(n), each Arrays.equals() is O(26) constant
    // SC: O(1) - frequency arrays are fixed size 26, independent of input size
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();

        // frequency count of characters in s1 (the target pattern)
        int[] s1Chars = new int[26];
        for(char ch : s1.toCharArray()){
            s1Chars[ch - 'a']++;
        }

        // frequency count of characters in the current window of s2
        int[] s2Chars = new int[26];
        int left = 0;

        for(int right = 0; right < s2Len; right++){
            char ch = s2.charAt(right);
            s2Chars[ch - 'a']++; // expand window by including s2[right]

            // window has reached size s1Len, compare frequency counts
            if(right - left + 1 >= s1Len){
                if(Arrays.equals(s1Chars, s2Chars)){
                    return true; // current window is a permutation of s1
                }
                // shrink window from the left to keep size == s1Len
                char leftChar = s2.charAt(right + 1 - s1Len);
                s2Chars[leftChar - 'a']--;
                left++;
            }
        }
        return false; // no window matched
    }
}