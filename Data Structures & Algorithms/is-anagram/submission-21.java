// ---------------------------------------------------------------
// Approach: Frequency counting using a fixed-size array (26 letters)
// Two strings are anagrams if every character appears the same
// number of times in both. We increment counts for s and decrement
// for t using the SAME array — if they're anagrams, everything
// cancels out to zero.
// Overall Time Complexity: O(n)  -> n = length of the strings
// Overall Space Complexity: O(1) -> fixed 26-slot array, independent of input size
// ---------------------------------------------------------------

class Solution {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        // Edge case: different lengths can never be anagrams — exit early.
        // O(1) check, saves us from doing unnecessary work below.
        if (sLen != tLen) return false;

        // Frequency array: one slot per lowercase letter (a-z -> index 0-25).
        // O(1) space since size is always fixed at 26, regardless of input.
        char[] charArr = new char[26];

        // Pass 1: count characters of s by incrementing their slot.
        // O(sLen) time.
        //"cancel out" characters of t by decrementing the same slot
        for (int i = 0; i < sLen; i++) {
            charArr[s.charAt(i) - 'a']++;
            charArr[t.charAt(i) - 'a']--;
        }

        // Pass 3: if s and t were anagrams, every slot should have net
        // zero (equal increments and decrements). Any nonzero slot means
        // a character count mismatch -> not an anagram.
        // O(26) -> constant time, doesn't scale with input.
        for (int i = 0; i < 26; i++) {
            if (charArr[i] != 0) {
                return false;
            }
        }

        // All counts balanced out -> s and t are anagrams.
        return true;
    }
}
