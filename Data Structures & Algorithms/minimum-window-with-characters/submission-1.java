class Solution {
    public String minWindow(String s, String t) {
         int sLen = s.length();
         int tLen = t.length();

         if(sLen < tLen || t == null) return "";

         int[] mapS = new int[256];
         int[] mapT = new int[256];

         for(char ch : t.toCharArray()){
            mapT[ch]++;
         }

         int left = 0, right = 0;
         int minLen  = Integer.MAX_VALUE;
         int minStart = 0;

         for(; right <sLen; right++){
            mapS[s.charAt(right)]++;

            while(contains(mapS, mapT)){
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    minStart = left;
                }
                mapS[s.charAt(left++)]--; // shrink left side and verify all the characters of t present in current window, if yes then we will get minimum window than previous window right
            }
         }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public boolean contains(int[] mapS, int[] mapT){
        for(int i = 0; i < 256; i++){
            if(mapT[i] > mapS[i]){
                return false;
            }
        }
        return true;
    }
}
