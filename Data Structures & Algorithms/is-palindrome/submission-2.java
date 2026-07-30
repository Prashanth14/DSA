class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return false;
        if(s.length() == 0) return true;

        int len = s.length();
        int i=0;
        String str = "";

        while(i < len){
            if(Character.isLetterOrDigit(s.charAt(i))){
                str += s.charAt(i);
                i++;
            }else{

                i++;
            }
        }

        int slen = str.length();
        int k = 0,  j= slen-1;

        while (k < j){
            if(Character.toLowerCase(str.charAt(k)) != Character.toLowerCase(str.charAt(j))){
                return false;
            }else{
                k++;
                j--;
            }
        }
        return true;
    }
}
