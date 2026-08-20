class Solution {
    public String decodeString(String s) {
        Stack<StringBuilder> StringStack = new Stack<>();
        Stack<Integer> repCountStack = new Stack<>();
        int num = 0;
        StringBuilder current = new StringBuilder();

        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '['){
                repCountStack.push(num);
                StringStack.push(current);

                num = 0;
                current = new StringBuilder();
            }else if(ch == ']'){
                int repeat = repCountStack.pop();
                StringBuilder previous = StringStack.pop();

                for(int i =0; i< repeat; i++){
                    previous.append(current);
                }

                current = previous;
            }else{
                current.append(ch);
            }
        }
        return current.toString();
    }
}