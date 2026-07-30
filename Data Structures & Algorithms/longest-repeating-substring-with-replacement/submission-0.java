class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();

        int len = s.length();
        int maxLen = 0, left = 0, maxFreq = 0;

        for(int right = 0; right < len; right++){
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0)+1);

            maxFreq = Math.max(maxFreq, freq.get(c));

            if((right - left + 1)- maxFreq > k){
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar)-1);
                left++;
            }else{
                maxLen = Math.max(maxLen, right-left+1);
            }
        }
        return maxLen;
    }
}
