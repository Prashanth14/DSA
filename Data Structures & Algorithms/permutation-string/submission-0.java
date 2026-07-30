class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length(), s2Len = s2.length();
        if(s2Len < s1Len) return false;

        int[] windowArr = new int[26];
        int[] s1Arr = new int[26]; 

        int left = 0;

        for(int i = 0; i<s1Len; i++){
            s1Arr[s1.charAt(i)-'a']++;
        }
        
        for(int right = 0; right <s2Len; right++){
            //Expand
            windowArr[s2.charAt(right) - 'a']++;

            //Shrink
            if((right-left +1  >  s1Len)){
                windowArr[s2.charAt(left) - 'a']--;
                left++;
            }

             if((right-left + 1 == s1Len) && Arrays.equals(s1Arr, windowArr)){
                return true;
            }
        }
        return false;        
    }
}
