class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        int sLen = strs[0].length();
        String prefix = "";

        for(int i = 0; i < sLen; i++){
            char ch = strs[0].charAt(i);

            for(int j = 1; j < len; j++){
                if( i >= strs[j].length()) return prefix;
                
                if(strs[j].charAt(i) != ch){
                    return prefix;
                }
            }
            prefix += ch;
        }

        return prefix;
    }
}