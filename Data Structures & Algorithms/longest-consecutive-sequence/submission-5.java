class Solution {
    public int longestConsecutive(int[] nums) {
        //  //2.using sorting, set and List Time Complexity is O(n*logn)
        int len = nums.length;
        if(len == 0) return 0;
        //To Store unique elements in the set
        HashSet<Integer> set = new HashSet<>();

        for(int num: nums){
            set.add(num);
        }

        int longest = 0;

        for(int num: nums){
            if(!set.contains(num-1)){
                int length = 1;
                while(set.contains(num+length)){
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;

        // //3. Using HashMap -> Time Complexity O(n)
        // int len = nums.length;
        // if(len == 0) return 0;
        // int longestSeqLen = 0;

        // HashMap<Integer, Boolean> exploredMap = new HashMap<>();

        // for(int num: nums){
        //     exploredMap.put(num, Boolean.FALSE);
        // }

        // for(int num: nums){
        //     int currentSeqLen = 1;

        //     int nextNum = num+1;
        //     while(exploredMap.containsKey(nextNum) && exploredMap.get(nextNum) == false){
        //         currentSeqLen++;
        //         exploredMap.put(nextNum, Boolean.TRUE);
        //         nextNum++;
        //     }

        //     int prevNum = num-1;
        //     while(exploredMap.containsKey(prevNum) && exploredMap.get(prevNum) == false){
        //         currentSeqLen++;
        //         exploredMap.put(prevNum, Boolean.TRUE);
        //         prevNum--;
        //     }

        //     longestSeqLen = Math.max(longestSeqLen, currentSeqLen);
        // }
        // return longestSeqLen;
    }
}
