class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1Len = nums1.length;
        int n2Len = nums2.length;
        int n = n1Len + n2Len;
        int[] arr = new int[n];

        int i=0;
        for(; i < n1Len; i++){
            arr[i] = nums1[i];
        }

        for(int j = 0; j < n2Len; j++){
            arr[i] = nums2[j];
            i++;
        }
        Arrays.sort(arr);

        if(n % 2 == 0){
            return ((double)arr[n/2] + (double)arr[n/2 - 1])/2.0;
        }else{
            return (double)(arr[n/2]);
        }
    }
}
