class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (right - left + 1 > k) {
            // whichever endpoint is FARTHER from x gets removed
            if (x - arr[left] <= arr[right] - x) {
                right--;           // right endpoint is farther (or tied) — drop it
            } else {
                left++;            // left endpoint is farther — drop it
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}