class Solution {
    // TC: O(log n + k) - binary search is O(log n), expanding window to size k is O(k)
    // SC: O(k) - for the result list (excluding output, extra space is O(1))
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0, right = len - 1;

        // binary search: find leftmost index where arr[index] >= x
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] < x){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        // left now points to first element >= x (or len-1 if none).
        // shift pointers so left is just before x's position, right is just after
        left = left - 1;
        right = left + 1;

        // expand the [left+1, right-1] window outward one element at a time,
        // always picking whichever side is closer to x, until window holds k elements
        // (right - left - 1 = current window size, since left/right are exclusive bounds)
        while(right - left - 1 < k){
            if(left < 0){
                // ran off the left edge, must take from the right
                right++;
            }else if(right >= len){
                // ran off the right edge, must take from the left
                left--;
            }else if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)){
                // left element is closer (or tied, and smaller value wins ties)
                left--;
            }else{
                // right element is strictly closer
                right++;
            }
        }

        // collect the k closest elements, window is (left, right) exclusive
        List<Integer> result = new ArrayList<>();
        for(int i = left + 1; i < right; i++){
            result.add(arr[i]);
        }
        return result;
    }
}