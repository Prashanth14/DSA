class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;

        int[] left = new int[len]; // right nearest small
        int[] right = new int[len]; // left nearest small
        Stack<Integer> st = new Stack<>();


        for(int x = len-1; x >= 0; x--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[x]){
                st.pop();
            }

            right[x] = (st.isEmpty()) ? len : st.peek();
            st.push(x);
        }

        st.clear();

        for(int y = 0; y < len; y++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[y]){
                st.pop();
            }

            left[y] = (st.isEmpty()) ? -1 : st.peek();
            st.push(y);
        }

        for (int i = 0; i < len; i++) {
            // Rectangle using heights[i] as its height.
            int currentArea = heights[i] * (right[i] - left[i] - 1);

            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }
}
