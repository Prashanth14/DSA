class Solution {
    // TC: O(m + n) - each element from nums1 and nums2 is placed exactly once
    // SC: O(1) - merged in-place within nums1, no extra array used
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1;   // pointers to last real elements of nums1 and nums2
        int len = m+n-1;        // pointer to last slot in nums1 to fill

        // fill from the back with the larger of the two current elements
        while(len >= 0 && i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[len] = nums1[i--];
            }else{
                nums1[len] = nums2[j--];
            }
            len--;
        }

        // if nums2 still has leftover (smaller) elements, copy them over
        // (leftover nums1 elements are already in their correct place, no copy needed)
        while(len >= 0 && j >= 0){
            nums1[len--] = nums2[j--];
        }
    }
}