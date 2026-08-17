class Solution {
    public String simplifyPath(String path) {
        
        Stack<String> st = new Stack<>();
        StringBuilder dirName = new StringBuilder();
        path = path + "/";

        for(char ch : path.toCharArray()){
            if(ch != '/'){
                dirName.append(ch);
            }else {
                String dir = dirName.toString();
                if(dir.equals("..")){
                    if(!st.isEmpty()){
                        st.pop();
                    }
                }else if(!dir.equals(".") && !dir.equals("")){
                    st.push(dir);
                }
                dirName.setLength(0);
            }
        }

        if(st.isEmpty()){
            return "/";
        }

        StringBuilder res = new StringBuilder();

        for(String dir: st){
            res.append("/").append(dir);
        }

        return res.toString();
    }
}