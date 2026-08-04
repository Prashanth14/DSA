class Solution {
    // TC: O(n) - main loop scans once; on the single mismatch (if any),
    //     isPalindrome scans its half at most once more.
    // SC: O(1) - only pointers used, no extra string/array built.
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                // characters match, shrink window
                left++;
                right--;
            } else {
                // mismatch found: try deleting either the left or right char.
                // whichever remaining range is a palindrome decides the answer.
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
        }
        // no mismatch ever found -> string was already a palindrome
        return true;
    }

    // checks if s[i..j] is a palindrome using two pointers
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}