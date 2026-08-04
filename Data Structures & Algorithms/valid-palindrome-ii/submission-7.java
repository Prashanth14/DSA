class Solution {
    public boolean validPalindrome(String s) {
        int len = s.length();
        // // if len == 1 then definitely it is a palindrome
        // // if len == 2 if two characters are same then its a palindrome, if differnt, we can delete atmost 1 character,
        // // then we will be left with only one character which is a palindrome, so len == 1 or len ==2 we will retur true;
        // if(len == 1 || len== 2) return true;
        
        // // chacking given string itself is already palindrome or not, if yes then we directly return true
        // String str = new StringBuilder(s).reverse().toString();
        // if(str.equals(s)){
        //     return true;
        // }
        
       int left = 0, right = len -1;

       while(left < right){
        if(s.charAt(left) == s.charAt(right)){
            left++;
            right--;
        }else{
            if(left <= right-1 && isPalindrome(s, left, right-1)){
               return true;
            }else if(left+1 <= right && isPalindrome(s, left + 1, right)){
                return true;
            }else{
                return false;
            }
        }
       }

       return true;
    }

    public boolean isPalindrome(String s , int i, int j){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}