class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        HashMap<Character, Integer> freq = new HashMap<>();
        int maxWindow = 0;
        int maxFreq = 0;
        int left = 0;

        for(int right = 0; right < len; right++){
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            maxFreq = Math.max(maxFreq, freq.get(ch));
           

            if((right - left + 1) - maxFreq > k){
                freq.put(s.charAt(left), freq.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }

            maxWindow = Math.max(maxWindow, right - left + 1);

        }

        return maxWindow;
    }
}
