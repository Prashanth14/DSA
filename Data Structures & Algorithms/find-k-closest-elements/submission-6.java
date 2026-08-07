class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //find the index of the x element using binary search
        int len = arr.length;
        int left = 0, right = len -1;

        while(left < right){
            int mid = (left + right)/2;

            if(arr[mid] < x){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        // use left pointer to the left of x and right pointer to the right of x
        left = left -1;
        right = left + 1;

        // calculate the absolute difference arr[left]-x and arr[right]-x until right - left - 1 < k (-1 is to exclude x element)
        while(right - left - 1 < k){
            if(left < 0){
                right++;
            }else if(right >= len){
                left--;
            }else if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)){
                left--;
            }else{
                right++;
            }

        }
          List<Integer> result = new ArrayList<>();
          for(int i = left + 1; i < right; i++){
            result.add(arr[i]);
          }
        return result;
    }
}