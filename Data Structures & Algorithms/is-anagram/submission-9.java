class Solution {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        //one of the edge case is if two strings lengths are not equal then obiviously we rerturn false
        if(sLen != tLen) return false;

        //character array
        char[] charArr = new char[26];

        for(int i = 0; i< sLen; i++){
            charArr[s.charAt(i)-'a']++;
        }

        for(int i = 0; i <tLen; i++){
            charArr[t.charAt(i)-'a']--;
        }

        for(int i = 0; i < 26; i++){
            if(charArr[i] != 0){
                return false;
            }
        }
        return true;
    }
}
