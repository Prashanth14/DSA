class Solution {
    public int calPoints(String[] operations) {
        int len = operations.length;
        if(len == 0) return 0;

        //create Stack 
        // iterate the array, check the operations "+", "C", "D" if one among them then perform required operation
        //  -> '+' take top records and perform sum and add sum to the stack again
        //  -> "c" remove previous score from the stack
        // -> "D" then pop top ele from stack then double it and push into stack
        // else push into the stack

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
                    int num1 = st.pop();
                    int sum = num1 + num2;

                    st.push(num1);
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