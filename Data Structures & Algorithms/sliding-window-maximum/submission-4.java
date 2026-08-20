class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int len = nums.length;
        int[] output = new int[len - k +1];
        int idx = 0;

        for(int i = 0; i< len; i++){
            maxHeap.offer(new int[]{nums[i], i});

            if(i >= k -1){
                while(maxHeap.peek()[1] <= i - k){
                    maxHeap.poll();
                }
                output[idx++] = maxHeap.peek()[0];
            }
        }
        return output;
    }
}
