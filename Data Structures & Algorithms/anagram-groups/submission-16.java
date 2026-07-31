class Solution {
    // Approach: build a "count key" per string (26 letter counts,
    // no sorting). Anagrams always produce the identical count key,
    // so group by that key.
    // TC: O(n * k) -> n strings, one pass over each (k = string length)
    // SC: O(n)     -> keys are fixed-length (26 numbers), not length-k
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        List<List<String>> result = new ArrayList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            // One slot per letter a-z. Fixed size -> O(1) space per string.
            int[] counts = new int[26];

            // Count each character. O(k) time (k = length of this string).
            for (char ch : strs[i].toCharArray()) {
                counts[ch - 'a']++;
            }

            // Turn counts into a string key, e.g. "1#1#1#0#0#...".
            // Always 26 numbers -> fixed length, independent of string length.
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : counts) {
                keyBuilder.append(count).append('#');
            }
            String key = keyBuilder.toString();

            // Get-or-create the group for this key, then add the string.
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(strs[i]);
        }

        // Collect all groups into the result list.
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }
}

// Why this is optimal:
//
// Time can't go below O(n * k) - every character of every string must
// be read at least once to know what letters it contains; there's no
// shortcut around that. This code does exactly one pass per string
// (no sorting, no repeated work), so it sits right at that floor.
//
// Space is where this beats the sorted-string version: the key here
// is always a fixed length (26 numbers + 26 '#'s), no matter how long
// the original string is. So storing n keys costs O(n) total, not
// O(n * k). Sorting-based keys grow with string length, so this
// count-based key is strictly cheaper.
//
// Net result: O(n * k) time, O(n) space - both dimensions beat the
// sorting approach (O(n * k log k) time, O(n * k) space), with no
// trade-off either way. That combination - hitting the theoretical
// time floor while also minimizing space - is what makes this the
// optimal answer for the given constraint (lowercase English letters,
// fixed 26-letter alphabet).