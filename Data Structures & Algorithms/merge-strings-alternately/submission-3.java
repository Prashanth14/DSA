class Solution {
    public String mergeAlternately(String word1, String word2) {
        int w1Len = word1.length();
        int w2Len = word2.length();
        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0;

        while(i < w1Len && j < w2Len){
            sb.append(word1.charAt(i)).append(word2.charAt(j));
            i++;
            j++;
        }

        while(i < w1Len){
            sb.append(word1.charAt(i));
            i++;
        }

        while(j < w2Len){
            sb.append(word2.charAt(j));
            j++;
        }

        return sb.toString();
    }
}