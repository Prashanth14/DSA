class Solution {
    public int lengthOfLongestSubstring(String s) {
      int len = s.length();
        if(len == 0) return 0;

        int maxLen = 0;

        int left = 0;
        HashMap<Character, Integer> freq = new HashMap<>();

        for(int right = 0; right < len; right++){
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0)+1);

            while(freq.get(c ) > 1){
                char letfChar = s.charAt(left);
                freq.put(letfChar, freq.get(letfChar)-1);
                left++;
            }

            maxLen = Math.max(maxLen, right-left+1);
        }
        return maxLen;
    }
}
