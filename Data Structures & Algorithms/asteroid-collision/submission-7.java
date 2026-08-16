class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for(int ast : asteroids){
            while(!st.isEmpty() && st.peek() > 0 && ast < 0 ){
                int diff = ast + st.peek();
                if(diff < 0){
                    st.pop();
                }else if(diff > 0){
                    ast = 0;
                }else{
                    ast = 0;
                    st.pop();
                }
            }

            if(ast != 0){
                st.add(ast);
            }
        }
        int[] result = st.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }
}
