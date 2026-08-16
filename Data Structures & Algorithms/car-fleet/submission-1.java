class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        int[][] pair = new int[len][2];

        for(int i = 0; i<len; i++){
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));

        Stack<Double> st = new Stack<>();

        for(int[] p: pair){
            st.push((double)(target - p[0])/ p[1]);
            if(st.size() >= 2 && st.peek() <= st.get(st.size()-2)){
                st.pop();
            }
        }
        return st.size();
    }
}
