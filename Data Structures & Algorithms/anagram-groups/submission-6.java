class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int len = strs.length;

        //HashMap
        Map<String, ArrayList<String>> map = new HashMap<>();

        for(int i = 0; i<len; i++){
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);

            map.put(sortedStr, new ArrayList<>());
        }

        for(int i=0; i<len; i++){
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);

            if(map.containsKey(sortedStr)){
                map.get(sortedStr).add(strs[i]);
            }
        }
        
        for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
