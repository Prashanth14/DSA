class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length()-1;

        while(left < right){
            while(!Character.isLetterOrDigit(s.charAt(left)) && left < right){
               left++;
            }
            char leftChar = s.charAt(left);
            leftChar = Character.toLowerCase(leftChar);

            while(!Character.isLetterOrDigit(s.charAt(right)) && left < right){
                right--;
            }
            char rightChar = s.charAt(right);
            rightChar = Character.toLowerCase(rightChar);

            if(leftChar == rightChar){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }
}
