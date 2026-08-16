class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];

        Stack<int[]> st = new Stack<>();

        for(int i = 0; i< len; i++){
            int t = temperatures[i];

            while(!st.isEmpty() && t > st.peek()[0]){
                int[] pair = st.pop();
                res[pair[1]] = i - pair[1];
            }
            st.push(new int[]{t, i});
        }
        return res;
    }
}
