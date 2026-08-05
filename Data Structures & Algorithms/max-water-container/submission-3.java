class Solution {
    public int maxArea(int[] heights) {
        int maxWater = 0;
        int len = heights.length;

        int left = 0;
        int right = len-1;

        while(left < right){
            int height = Math.min(heights[left], heights[right]);
            int width = right - left;
            maxWater = Math.max(maxWater, height * width);

            if(heights[left] < heights[right]){
                left++;
            }else{
                right--;
            }
           
        }
        return maxWater;
    }
}
