class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        // res = the index where target WOULD be inserted if not found.
        // Defaults to len — meaning "target is bigger than everything,
        // insert it at the very end" — in case the loop never updates it.
        int res = len;

        while (left <= right) {
            // mid calculated this way (not (left+right)/2) avoids
            // potential integer overflow for very large left/right values.
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Exact match found -> that's the answer, done.
                return mid;
            }

            if (nums[mid] < target) {
                // target is bigger than nums[mid] -> it must be somewhere
                // to the right -> discard the left half (including mid).
                left = mid + 1;

            } else if (nums[mid] > target) {
                // target is smaller than nums[mid] -> nums[mid] is a
                // CANDIDATE insertion point (target would go right before
                // it, IF nothing smaller and still > target is found later).
                // Record it, then keep searching the left half for an
                // even earlier valid insertion point.
                res = mid;
                right = mid - 1;
            }
        }

        // Loop ends without finding an exact match.
        // res holds the smallest index whose value is > target seen
        // during the search — i.e., the correct sorted insertion position.
        return res;
    }
}