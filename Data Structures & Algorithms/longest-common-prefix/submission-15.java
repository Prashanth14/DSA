class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        int sLen = strs[0].length();
        int matchedLen = 0;

        for(int i = 0; i < sLen; i++){
            char ch = strs[0].charAt(i);

            for(int j = 1; j < len; j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != ch){
                    return strs[0].substring(0, matchedLen);
                }
            }
            matchedLen++;
            
        }
        return strs[0].substring(0, matchedLen);
    }
}