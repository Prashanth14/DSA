class Solution {
    // Approach: Max-heap with lazy deletion
    // TC: O(n log n) - each of the n elements is offered once and polled at most once,
    //     each heap operation costs O(log n)
    // SC: O(n) - heap can grow up to n entries before stale ones are cleared out
    public int[] maxSlidingWindow(int[] nums, int k) {
        // max-heap of [value, index] pairs, ordered by value descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int len = nums.length;
        int[] output = new int[len - k + 1]; // one max per window position
        int idx = 0;

        for(int i = 0; i < len; i++){
            // add current element (with its index) to the heap
            maxHeap.offer(new int[]{nums[i], i});

            // window [i-k+1, i] is only fully formed once i >= k-1
            if(i >= k - 1){
                // heap's top is the largest value seen so far overall, but its
                // index might belong to a position that already slid out of the
                // window (window covers indices [i-k+1, i], so anything with
                // index <= i-k is stale/expired).
                // Keep discarding stale tops until the top is actually inside the window.
                while(maxHeap.peek()[1] <= i - k){
                    maxHeap.poll();
                }
                // top is now guaranteed to be within the window -> it's the window's max
                output[idx++] = maxHeap.peek()[0];
            }
        }
        return output;
    }
}