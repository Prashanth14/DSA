class Solution {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) return false;

        //HashMap
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < sLen; i++){
            char sCh = s.charAt(i);
            map.put(sCh, map.getOrDefault(sCh, 0)+1);
        }


        for(int i = 0; i < tLen; i++){
            char tCh = t.charAt(i);
            map.put(tCh, map.getOrDefault(tCh, 0)-1);
        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() < 0){
                return false;
            }
        }
        return true;
    }
}
