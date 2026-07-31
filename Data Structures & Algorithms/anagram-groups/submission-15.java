class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        List<List<String>> result = new ArrayList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();

        for(int i = 0; i<len; i++){
            int[] counts = new int[26];

            for(char ch: strs[i].toCharArray()){
                counts[ch - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();

            for(int count: counts){
                keyBuilder.append(count).append('#');
            }

            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(strs[i]);
        }

        for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            result.add(entry.getValue());
        }

        return result;
    }
}
