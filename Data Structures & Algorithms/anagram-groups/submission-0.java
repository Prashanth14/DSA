class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
       int len = strs.length;
         HashMap<String, List<String>> map = new HashMap<>();

         for(int i =0; i<len; i++){
            String str = strs[i]; 
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);

            // if(!map.containsKey(sortedStr)){
            //     map.put(sortedStr, new ArrayList<>());
            // }
            map.putIfAbsent(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(strs[i]);
         }
         return new ArrayList<>(map.values());
    }
}
