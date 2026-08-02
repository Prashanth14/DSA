class Solution {

    // Approach: length-prefix encoding. Each string is written as
    // <length>#<content>. Decoding reads the length first, so it
    // knows exactly how many characters to consume next — no
    // ambiguity even if the content itself contains '#' or digits.
    //
    // TC: O(n + m) -> n = number of strings, m = total characters.
    //     O(1) overhead per string (length digits + '#') + O(content length)
    // SC: O(n + m) -> encoded string / decoded list both hold all n
    //     strings' content plus their length-prefix overhead
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length())  // write length first
              .append('#')           // marker: length ends here, content starts next
              .append(str);          // actual string content
        }

        String encodedStr = sb.toString();
        return encodedStr;
    }

    public List<String> decode(String str) {
        List<String> decodedStrs = new ArrayList<>();
        int len = str.length();

        int i = 0;
        while (i < len) {
            int j = i;

            // scan forward to find '#' marking end of the length prefix
            while (str.charAt(j) != '#') {
                j++;
            }

            int length = Integer.parseInt(str.substring(i, j)); // digits before '#'
            String content = str.substring(j + 1, j + 1 + length); // exactly 'length' chars after '#'
            decodedStrs.add(content);

            i = j + 1 + length; // jump past this string, to the next length prefix
        }

        return decodedStrs;
    }
}