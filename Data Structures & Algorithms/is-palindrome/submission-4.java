// TC: O(n) — each character visited once by left/right pointers combined.
// SC: O(1) — only two pointers and two char variables used.
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // skip non-alphanumeric chars from the left
            while (!Character.isLetterOrDigit(s.charAt(left)) && left < right) {
                left++;
            }
            char leftChar = Character.toLowerCase(s.charAt(left));

            // skip non-alphanumeric chars from the right
            while (!Character.isLetterOrDigit(s.charAt(right)) && left < right) {
                right--;
            }
            char rightChar = Character.toLowerCase(s.charAt(right));

            // mismatch means not a palindrome
            if (leftChar != rightChar) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}