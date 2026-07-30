class Solution {

    public String encode(List<String> strs) {
        String res ="";

        for(String str: strs){
            res += (str + ".");
        }
        return res;
    }

    public List<String> decode(String str) {
        char[] charArr = str.toCharArray();
        int len = charArr.length;
        List<String> res = new ArrayList<>();
        String s= "";

        for(int i =0; i<len; i++){
            if(charArr[i] != '.'){
                s += charArr[i];
            }else{
                res.add(s);
                s = "";
            }
        }
        return res;
    }
}
