class Solution {
    public boolean isAnagram(String s, String t) {
        char[] sCharArr = s.toCharArray();
        Arrays.sort(sCharArr);
        String sortredsString = new String(sCharArr);

        char[] tCharArr = t.toCharArray();
        Arrays.sort(tCharArr);
        String sortedtString = new String(tCharArr);

        if(sortredsString.equals(sortedtString)){
            return true;
        }
        return false;
    }
}
