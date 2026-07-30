class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();

        int len = arr.length;
        int left = 0;

        // Slide to right when the incoming right edge is closer than the outgoing left edge
        while( left + k < len && (Math.abs(arr[left+k]-x) <  Math.abs(arr[left]-x) || arr[left + k] == arr[left])){
            left++;
        }

        for(int i =left; i< left+k; i++){
            res.add(arr[i]);
        }

        return res;
    }
}