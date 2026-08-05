class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();

        Set<Character> set = new HashSet<>();

        int left = 0;
        int longest = 0;

        //xyz longest = 3
       for(int right = 0; right < len; right++){
            char ch = s.charAt(right);
            while(set.contains(ch)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            longest = Math.max(right - left + 1, longest);
        }
        return longest;
    }
}
