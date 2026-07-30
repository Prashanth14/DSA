class Solution {

    public String encode(List<String> strs) {
        String SingleStr = "";
        for(String str: strs){
            SingleStr += str;
            SingleStr+= "/";
        }
        return SingleStr;
    }

    public List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        int sLen = str.length();
        String temp = "";
        for(int i =0; i<sLen; i++){
            if(str.charAt(i) != '/'){
                temp += str.charAt(i);
            }else{
                strs.add(temp);
                temp = "";
            }
        }
        return strs;
    }
}
