class Solution {
    // TC: O((m+n) log(m+n)) - dominated by Arrays.sort() over the merged array
    // SC: O(log(m+n)) to O(m+n) - space used internally by the sort algorithm
    //     (Java's dual-pivot quicksort for primitives; not counting nums1 itself)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // copy all elements of nums2 into the empty tail slots of nums1
        for(int i = 0; i < n; i++){
            nums1[m] = nums2[i];
            m++;
        }

        // sort the combined array in place
        Arrays.sort(nums1);
    }
}