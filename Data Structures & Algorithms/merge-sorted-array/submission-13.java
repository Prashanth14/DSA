class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1;
        int len = m+n-1;

        while(len >= 0 && i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[len] = nums1[i--];
            }else{
                nums1[len] = nums2[j--];
            }
            len--;
        }

        while(len >= 0 && j >= 0){
            nums1[len--] = nums2[j--];
        }
    }
}