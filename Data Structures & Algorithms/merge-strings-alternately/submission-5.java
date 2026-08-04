class Solution {
    // TC: O(n + m) - every character of word1 and word2 is visited exactly once
    // SC: O(n + m) - output StringBuilder holds all characters from both strings
    public String mergeAlternately(String word1, String word2) {
        int w1Len = word1.length();
        int w2Len = word2.length();
        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0;

        // append characters alternately while both strings still have chars left
        while(i < w1Len && j < w2Len){
            sb.append(word1.charAt(i)).append(word2.charAt(j));
            i++;
            j++;
        }

        // append any leftover characters from word1 (if it was longer)
        while(i < w1Len){
            sb.append(word1.charAt(i));
            i++;
        }

        // append any leftover characters from word2 (if it was longer)
        while(j < w2Len){
            sb.append(word2.charAt(j));
            j++;
        }

        return sb.toString();
    }
}