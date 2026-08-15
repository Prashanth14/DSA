class Solution {
    public int evalRPN(String[] tokens) {
        //create an empty stack
        // insert all the integers into the stack
        // whenever you see any of the operators "+", "-", "*", or "/" pop top 2 numbers from stack perform operation and push result into the stack
        // at the end we will have final result in stack, pop and return 

        Stack<Integer> st =  new Stack<>();

        for(String s : tokens){
            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")){
                st.push(Integer.parseInt(s));
            }else{  
                if(s.equals("+")){
                    st.push(st.pop()+ st.pop());
                }else if(s.equals("-")){
                    int num2 = st.pop();
                    int num1 = st.pop();
                    st.push(num1-num2);
                }else if(s.equals("*")){
                    st.push(st.pop() * st.pop());
                }else if(s.equals("/")){
                    int num2 = st.pop();
                    int num1 = st.pop();
                    st.push(num1/num2);
                }
            }
        }
        return st.pop();
    }
}
