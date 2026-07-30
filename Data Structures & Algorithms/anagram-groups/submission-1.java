class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
    //     //Time complexity: O(m∗nlogn)
    //    int len = strs.length;
    //      HashMap<String, List<String>> map = new HashMap<>();

    //      for(int i =0; i<len; i++){
    //         String str = strs[i]; 
    //         char[] charArr = str.toCharArray();
    //         Arrays.sort(charArr);
    //         String sortedStr = new String(charArr);

    //         // if(!map.containsKey(sortedStr)){
    //         //     map.put(sortedStr, new ArrayList<>());
    //         // }
    //         map.putIfAbsent(sortedStr, new ArrayList<>());
    //         map.get(sortedStr).add(strs[i]);
    //      }
    //      return new ArrayList<>(map.values());




    //2. Using Hash Table -> Time Complexity O(m * n) where
     //m is the number of strings and n is the length of the longest string
    HashMap<String, List<String>> map = new HashMap<>();

    for(String str: strs){

        int[] count = new int[26];
        for(char ch: str.toCharArray()){
            count[ch -'a']++;
        }

        String key = Arrays.toString(count);

        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(str);
    }
    return new ArrayList<>(map.values());
    }
}
