class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //1. using Bucket Sort and Map -> Time Complexity O(n), Space Complexity O(n)-> for storing elements in the bucket
       int len = nums.length;
       //create a bucket Sort and HashMap
       List<Integer>[] bucket = new List[len+1];
       HashMap<Integer, Integer> map = new HashMap<>();

       
       //Storing numbers and its frequency into the HashMap
       for(int i =0; i<len; i++){
        map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
       }

       // Storing keys, here frequency will acts as index in the bucket, values we will store
       for(int key: map.keySet()){
        //for every element we will get the frequency
        int frequency = map.get(key);
        //if the bucket is empty at that frequency index, we will create new Array List to store keys(i.e, numbers)
        if(bucket[frequency] == null){
            bucket[frequency] = new ArrayList<>();
         }

       // adding values at the frequency index
       bucket[frequency].add(key);
       }
    
    //create result array of size k
    int[] res = new int[k];
    int counter = 0, blen = bucket.length-1;

    for(int pos = blen; pos >=0  && counter < k; pos--){
        if(bucket[pos] != null){
            for(Integer integer: bucket[pos]){
                res[counter++] = integer;
            }
        }
    }
    return res;
    }
}
