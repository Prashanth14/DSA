class Solution {

    public String encode(List<String> strs) {
        // String SingleStr = "";
        // for(String str: strs){
        //     SingleStr += str;
        //     SingleStr+= "/";
        // }
        // return SingleStr;
        
        //USing StringBuilder
        StringBuilder encodedString = new StringBuilder();
        for(String str: strs){
            encodedString.append(str.length()).append('#').append(str);
        }
        return encodedString.toString();
    }

    public List<String> decode(String str) {
        // List<String> strs = new ArrayList<>();
        // int sLen = str.length();
        // String temp = "";
        // for(int i =0; i<sLen; i++){
        //     if(str.charAt(i) != '/'){
        //         temp += str.charAt(i);
        //     }else{
        //         strs.add(temp);
        //         temp = "";
        //     }
        // }
        // return strs;


        //Alternate way
        List<String> strs = new ArrayList<>();
        int len = str.length();
        for(int i =0; i<len; ){
            int j = i;
            while(str.charAt(j) != '#') j++;

            int slen = Integer.valueOf(str.substring(i, j));
            i = j+1+slen;
            strs.add(str.substring(j+1, i));
        }
        return strs;
    }
}
