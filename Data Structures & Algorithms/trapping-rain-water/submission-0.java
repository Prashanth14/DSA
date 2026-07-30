class Solution {
    public int trap(int[] height) {
        int h = height.length;
        if(height == null || h == 0) return 0;

        int[] leftMax = new int[h];
        int[] rightMax = new int[h];
        leftMax[0] = height[0];
        rightMax[h-1] = height[h-1];

        for(int i = 1; i < h; i++){
           leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        for(int j = h-2; j >= 0; j--){
            rightMax[j] = Math.max(rightMax[j+1], height[j]);
        }

        int water = 0;
        for(int k = 0; k<h; k++){
            water += (Math.min(leftMax[k], rightMax[k])) - height[k];
        }
        return water;
    }
}
