class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        for(String s : strs){
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);
            
            map.putIfAbsent(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
