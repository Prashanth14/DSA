class Solution {
    public String simplifyPath(String path) {
        
        Stack<String> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        StringBuilder dirName = new StringBuilder();
        String dir ="";

        for(char ch : path.toCharArray()){
            if(ch != '/'){
                dirName.append(ch);
            }else {
                dir = dirName.toString();
                if(dir.equals("..")){
                    if(!st.isEmpty()){
                        st.pop();
                    }
                }else if(!dir.equals(".") && !dir.equals("")){
                    st.push(dir);
                }
                dirName = new StringBuilder();
            }
        }
        dir = dirName.toString();
        if(dir.equals("..")){
            if(!st.isEmpty()){
                st.pop();
            }
        }else if(!dir.equals(".") && !dir.equals("")){
            st.push(dir);
        }

        Stack<String> revSt = new Stack<>();


        while(!st.isEmpty()){
            revSt.push(st.pop());
        }
        while(!revSt.isEmpty()){
            res.append('/').append(revSt.pop());
        }
        if (res.length() == 0) {
            return "/";
        }

        return res.toString();
    }
}