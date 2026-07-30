class Solution {
    // ---------------------------------------------------------------
    // Approach: Sort both strings and compare the results.
    // If two strings are anagrams, sorting rearranges both into the
    // exact same character sequence — so sorted versions being equal
    // is proof the original strings had the same characters with the
    // same frequencies.
    //
    // Overall Time Complexity: O(n log n) -> dominated by sorting
    // Overall Space Complexity: O(n) -> new char arrays + new String objects
    //                                   (see notes below on why it's NOT O(1))
    // ---------------------------------------------------------------
    public boolean isAnagram(String s, String t) {

        // Convert s into a char array so it can be sorted.
        // toCharArray() creates a brand NEW array of size n -> O(n) space.
        char[] sCharArr = s.toCharArray();

        // Sort in place (dual-pivot quicksort). O(n log n) time.
        // Note: "in place" only means it doesn't copy sCharArr again —
        // it does NOT mean the overall solution is O(1) space, since we
        // already paid O(n) to create sCharArr above.
        Arrays.sort(sCharArr);

        // Rebuild a String from the sorted array -> another O(n) allocation.
        String sortredsString = new String(sCharArr);

        // Repeat the same three steps for t.
        char[] tCharArr = t.toCharArray();
        Arrays.sort(tCharArr);
        String sortedtString = new String(tCharArr);

        // Compare the two sorted strings. equals() checks length first,
        // so this also implicitly handles the "different length" edge
        // case correctly, even without an explicit early check.
        // O(n) comparison, doesn't change overall complexity.
        if (sortredsString.equals(sortedtString)) {
            return true;
        }
        return false;
    }
}