class Solution {
    public boolean isDigit(char ch){
        if(ch >= '0' && ch <= '9'){
            return true;
        }
        return false;
    }

    public String decodeString(String s) {
        int len = s.length();
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for(int i = 0; i<len; i++){
            char ch = s.charAt(i);
            StringBuilder sb = new StringBuilder();

            if(ch != ']'){
                st.push(ch);
            }else if(ch == ']'){
                while(st.peek() != '['){
                    sb.append(st.pop());
                }
                sb.reverse();
                st.pop();

                int num = 0;
                int place = 1;
                while(!st.isEmpty() && isDigit(st.peek()) ){
                    num = num + (st.pop() - '0') * place;
                    place = place * 10;
                }

                String decoded = sb.toString();
                StringBuilder repeated = new StringBuilder();
                for(int j = 0; j < num; j++){
                    repeated.append(decoded);
                }
                for(int k = 0; k < repeated.length(); k++){
                    st.push(repeated.charAt(k));
                }
            }
        }
        while(!st.isEmpty()){
            res.append(st.pop());
        }

        return res.reverse().toString();
    }
}