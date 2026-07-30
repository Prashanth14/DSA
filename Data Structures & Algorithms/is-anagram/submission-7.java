class Solution {
    public boolean isAnagram(String s, String t) {
        int sLen =s.length(), tLen = t.length();
        int[] count = new int[26];

        if(sLen != tLen) return false;

        for(int i=0; i<sLen; i++ ){
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for(int num : count){
            if(num != 0) return false;
        }
        return true;
    }
}
