class Solution {
    // Approach: sort each string to get a canonical "anagram key".
    // Anagrams sort to the identical string, so group by that key.
    // computeIfAbsent creates the group list on first sight of a key,
    // then adds the string in the same pass — one sort per string.
    // TC: O(n * k log k) -> n strings, each sorted once (k = max length)
    // SC: O(n * k) -> stores n sorted keys + result lists
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int len = strs.length;

        Map<String, ArrayList<String>> map = new HashMap<>();

        // Single pass: build key, get-or-create its group, add string to it.
        for (int i = 0; i < len; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);              // O(k log k) per string
            String sortedStr = new String(charArr);

            map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(strs[i]);
        }

        // Collect all groups into the result list.
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}