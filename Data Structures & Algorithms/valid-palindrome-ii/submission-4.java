class Solution {
    public boolean validPalindrome(String s) {
        int len = s.length();
        if(s == null) return false;
        if(len == 1) return true;

        int i = 0, j = len-1;

        while(i <= j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else{
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}