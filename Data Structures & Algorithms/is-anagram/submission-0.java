class Solution {
    public boolean isAnagram(String s, String t) {
         //1.sort strings and compare two strings are equal or not.

        //convert String to Character Array
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();

        //Sort Character Array
        Arrays.sort(sCharArr);
        Arrays.sort(tCharArr);

        //Convert character arrays back to String
        String sSortedArr = new String(sCharArr);
        String tSortedArr = new String(tCharArr);

        
        //Compare two Strings are Equal or not
        if(sSortedArr.equals(tSortedArr)) return true;

        return false;
    }
}
