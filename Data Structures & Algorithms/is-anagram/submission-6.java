class Solution {
    public boolean isAnagram(String s, String t) {
     HashMap<Character, Integer> map = new HashMap<Character, Integer>();
     int sLen =s.length(), tLen = t.length();
     if(sLen != tLen) return false;
   
        for(int i = 0; i<sLen; i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for(int i: map.values()){
            if(i != 0) return false;
        }

     return true;
    }
}
