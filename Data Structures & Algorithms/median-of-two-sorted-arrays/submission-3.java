class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1Len = nums1.length;
        int n2Len = nums2.length;
        //smaller array
        int[] smaller = n1Len > n2Len ? nums2 : nums1;
        //larger array
        int[] larger = n1Len > n2Len ? nums1 : nums2;
        //total length
        int totalLength = n1Len + n2Len;
        //partition on smaller array using Binary Search left = 0 and right = small len - 1
        int low = 0, high = smaller.length;
        while(low <= high){
            int partitionX = (high + low)/2;
            int partitionY = (totalLength + 1)/2 - partitionX;

            int l1 = partitionX == 0? Integer.MIN_VALUE : smaller[partitionX-1];
            int r1 = partitionX == smaller.length ? Integer.MAX_VALUE : smaller[partitionX];

            int l2 = partitionY == 0? Integer.MIN_VALUE : larger[partitionY-1];
            int r2 = partitionY == larger.length? Integer.MAX_VALUE : larger[partitionY];

            if(l1 <= r2 && l2 <= r1){
                if(totalLength % 2 == 0){
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }else{
                    return Math.max(l1, l2);
                }
            }

            if(l1 > r2){
                high = partitionX-1;
            }else{
                low = partitionX + 1;
            }
        }
      return 0;
    }
}
