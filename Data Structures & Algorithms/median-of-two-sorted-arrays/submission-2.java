class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        /*
         * Brute Force:
         * 1. Combine both arrays into one array.
         * 2. Sort the combined array.
         * 3. If total length is odd -> return middle element.
         * 4. If total length is even -> average the two middle elements.
         *
         * TC: O((m+n) log(m+n)) -> sorting dominates
         * SC: O(m+n) -> combined array
         */

        int n1Len = nums1.length;
        int n2Len = nums2.length;
        int n = n1Len + n2Len;

        // Store elements from both arrays.
        int[] arr = new int[n];

        int i = 0;

        // Copy nums1.
        for (; i < n1Len; i++) {
            arr[i] = nums1[i];
        }

        // Copy nums2.
        for (int j = 0; j < n2Len; j++) {
            arr[i] = nums2[j];
            i++;
        }

        // Sort the combined array.
        Arrays.sort(arr);

        // Even length -> median is average of two middle elements.
        if (n % 2 == 0) {
            return ((double) arr[n / 2] + arr[n / 2 - 1]) / 2.0;
        }

        // Odd length -> median is the middle element.
        return (double) arr[n / 2];
    }
}