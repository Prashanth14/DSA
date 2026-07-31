class Solution {
    // Approach: sort each string to get a canonical "anagram key".
    // Anagrams sort to the identical string, so group by that key.
    // TC: O(n * k log k) -> n strings, each sorted (k = max string length)
    // SC: O(n * k) -> stores n sorted keys + result lists
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int len = strs.length;

        Map<String, ArrayList<String>> map = new HashMap<>();

        // Pass 1: create an empty list for each anagram key.
        for (int i = 0; i < len; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);              // O(k log k) per string
            String sortedStr = new String(charArr);

            map.put(sortedStr, new ArrayList<>());
        }

        // Pass 2: sort again, then add original string to its group.
        for (int i = 0; i < len; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);              // repeated sort (redundant)
            String sortedStr = new String(charArr);

            if (map.containsKey(sortedStr)) {
                map.get(sortedStr).add(strs[i]);
            }
        }

        // Collect all groups into the result list.
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}