class Solution {
    // ---------------------------------------------------------------
    // Approach: Frequency counting using a HashMap<Character, Integer>.
    // Same idea as the fixed-size array version, but the map's keys
    // grow dynamically instead of being tied to a fixed alphabet.
    //
    // Overall Time Complexity: O(n)  -> n = length of the strings
    // Overall Space Complexity: O(k) -> k = number of distinct characters
    //                                   present (worst case O(n) if every
    //                                   character in the string is unique)
    // ---------------------------------------------------------------
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        // Edge case: different lengths can never be anagrams — exit early.
        // O(1) check.
        if (sLen != tLen) return false;

        // Frequency map: key = character, value = net count (s adds, t subtracts).
        // Unlike a fixed char[26] array, this only stores keys that actually
        // appear — no wasted space, and no assumption about the alphabet.
        HashMap<Character, Integer> map = new HashMap<>();

        // Pass 1: count characters of s by incrementing their entry.
        // getOrDefault(sCh, 0) handles the "first time seeing this char" case.
        // O(sLen) time, O(1) per operation (average case hashing).
        for (int i = 0; i < sLen; i++) {
            char sCh = s.charAt(i);
            map.put(sCh, map.getOrDefault(sCh, 0) + 1);
        }

        // Pass 2: "cancel out" characters of t by decrementing the same entry.
        // O(tLen) time. Since sLen == tLen (checked above), this is a fair
        // one-to-one comparison against pass 1.
        for (int i = 0; i < tLen; i++) {
            char tCh = t.charAt(i);
            map.put(tCh, map.getOrDefault(tCh, 0) - 1);
        }

        // Pass 3: if any count is negative, t used a character more times
        // than s had it -> not an anagram.
        // Note: we don't need to check for positive leftovers separately —
        // since sLen == tLen, the sum of all counts is guaranteed to be 0.
        // So if any value goes negative, some other value MUST be positive
        // to balance it out; checking "< 0" alone is enough to catch every
        // mismatch.
        // O(k) time, k = number of distinct characters in the map.
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0) {
                return false;
            }
        }

        return true;
    }
}