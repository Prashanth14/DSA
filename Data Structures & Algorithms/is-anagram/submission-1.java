class Solution {
    public boolean isAnagram(String s, String t) {
        //  //1.sort strings and compare two strings are equal or not.
        // // Time Complexity o(nlogn)
        // //convert String to Character Array
        // char[] sCharArr = s.toCharArray();
        // char[] tCharArr = t.toCharArray();

        // //Sort Character Array
        // Arrays.sort(sCharArr);
        // Arrays.sort(tCharArr);

        
        // //Compare two Strings are Equal or not
        // if(Arrays.equals(tCharArr, sCharArr)) return true;

        // return false;

        //2. store every character in the String into HashMap and check two HashMaps are Structurally equal, that is having same number of keys and values
        int slen = s.length(), tlen= t.length();
        if(slen != tlen) return false;

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for(int i =0; i<slen; i++){
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0)+1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0)+1);
        }

        if(sMap.equals(tMap)) return true;

        return false;
    }
}
