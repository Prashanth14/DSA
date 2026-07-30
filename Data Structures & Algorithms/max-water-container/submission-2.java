class Solution {
    public int maxArea(int[] heights) {
        int i = 0, j = heights.length-1;
        int max = Integer.MIN_VALUE;
        int maxWater = 0;

        while(i < j){
            if(heights[i] >= heights[j]){
                maxWater = heights[j] * (j-i);
                j--;
            }else{
                maxWater = heights[i] * (j-i);
                i++;
            }
            if(max < maxWater) max = maxWater;
        }
        return max;
    }
}
