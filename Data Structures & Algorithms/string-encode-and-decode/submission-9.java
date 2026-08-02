class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str: strs){
            sb.append(str.length()).append('#').append(str);
        }
        String encodedStr = sb.toString();
        return encodedStr;
    }

    public List<String> decode(String str) {
        List<String> decodedStrs = new ArrayList<>();
        int len = str.length();
        
        int i = 0;
        while(i < len){
            int j = i;

            while(str.charAt(j) != '#'){
                j++;
            }

            int length = Integer.parseInt(str.substring(i, j));
            String content = str.substring(j+1, j+ 1 + length);
            decodedStrs.add(content);

            i = j + 1 + length;
        }
        return decodedStrs;
    }
}
