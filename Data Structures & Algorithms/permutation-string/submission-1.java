class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();

        int[] s1Chars = new int[26];

        for(char ch : s1.toCharArray()){
            s1Chars[ch - 'a']++;
        }

        int[] s2Chars = new int[26];
        int left = 0;

        for(int right = 0; right < s2Len; right++){
            char ch = s2.charAt(right);
            s2Chars[ch - 'a']++;

            if(right - left + 1 >= s1Len){
                if(Arrays.equals(s1Chars, s2Chars)){
                    return true;
                }
                char leftChar = s2.charAt(right + 1 - s1Len);
                s2Chars[leftChar - 'a']--;
                left++;
            }
        }
        return false;
    }
}
