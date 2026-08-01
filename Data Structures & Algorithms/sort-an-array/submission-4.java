class Solution {
    // Approach: merge sort. Recursively split array in half until
    // single elements remain, then merge sorted halves back together.
    // TC: O(n log n) -> log n levels of splitting, O(n) work to merge each level
    // SC: O(n) -> temp array allocated during merge (not smallest space possible)
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        if (len <= 1) return nums; // already sorted

        mergeSort(nums, 0, len - 1);
        return nums;
    }

    // Splits [left, right] into two halves, sorts each, then merges them.
    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return; // base case: 0 or 1 element

        int mid = left + (right - left) / 2; // avoids overflow

        mergeSort(nums, left, mid);      // sort left half
        mergeSort(nums, mid + 1, right); // sort right half
        merge(nums, left, mid, right);   // merge sorted halves
    }

    // Merges two already-sorted subarrays: nums[left..mid] and nums[mid+1..right]
    public void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // holds merged result for this chunk
        int i = left;    // pointer into left half
        int j = mid + 1; // pointer into right half
        int k = 0;       // pointer into temp

        // Pick the smaller front element from either side each time
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Copy any leftover elements from whichever side didn't run out
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // Copy merged (sorted) result back into nums.
        // "left + x" maps temp's 0-indexed position back to nums' real position.
        for (int x = 0; x < temp.length; x++) {
            nums[left + x] = temp[x];
        }
    }
}