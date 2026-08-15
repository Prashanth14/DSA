class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        int len = s.length();

        if(len % 2 != 0) return false;

        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            }else if(ch == ')' || ch == '}' || ch == ']'){
                if(st.isEmpty()){
                    return false;
                }

                char top = st.peek();
                if((top == '(' && ch == ')') || (top == '{' && ch == '}') || (top == '[' && ch == ']')){
                    st.pop();
                }else{
                    return false;
                }
            }
        }

        if(st.isEmpty()){
            return true;
        }
        return false;
    }
}
