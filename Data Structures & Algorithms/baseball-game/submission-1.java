class Solution {
    public int calPoints(String[] operations) {
        int len = operations.length;
        if(len == 0) return 0;

        Stack<Integer> st = new Stack<>();

        for(String s : operations){
            if(!s.equals("+") && !s.equals("C") && !s.equals("D")){
                int n = Integer.parseInt(s);
                st.push(n);
            }else{
                if(s.equals("D")){
                    int top = st.pop();
                    st.push(top);
                    st.push(top * 2);
                }else if(s.equals("C")){
                    st.pop();
                }else if(s.equals("+")){
                    int num2 = st.pop();
                    int num1 = st.peek();
                    int sum = num1 + num2;

                    st.push(num2);
                    st.push(sum);
                }
            }
        }

        int result = 0;
        while(!st.isEmpty()){
            result += st.pop();
        }
        return result;
    }
}