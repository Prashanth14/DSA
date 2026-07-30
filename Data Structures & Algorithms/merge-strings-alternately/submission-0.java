class Solution {
    public String mergeAlternately(String word1, String word2) {
        int w1Len = word1.length(), w2Len = word2.length();
        int i =0, j = w1Len-1, k = 0, l = w2Len-1;
        String res = "";

        while(i <= j && k <= l){
           res += word1.charAt(i);
           res += word2.charAt(k);
           i++;
           k++;
        }

        while(i <= j){
            res += word1.charAt(i);
            i++;
        }

        while(k <= l){
            res += word2.charAt(k);
            k++;
        }

        return res;
    }
}