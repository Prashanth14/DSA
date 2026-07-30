class Solution {
    public int longestConsecutive(int[] nums) {
        //  //2.using sorting, set and List Time Complexity is O(n*logn)
        // int len = nums.length;
        // if(len == 0) return 0;
        // //To Store unique elements in the set
        // HashSet<Integer> set = new HashSet<>();

        // for(int num: nums){
        //     set.add(num);
        // }

        // //Convert set to List and sort list
        // ArrayList<Integer> uniqueList = new ArrayList<>(set);
        // Collections.sort(uniqueList);

        // int lsize = uniqueList.size();
        // int count = 1;
        // int i = 0;
        // int max = 0;
        // while(i<lsize-1){
        //     if((uniqueList.get(i+1) - uniqueList.get(i)) == 1){
        //         count++;
        //     }else if((uniqueList.get(i+1) - uniqueList.get(i)) > 1){
        //         max = Math.max(count, max);
        //         count = 1;
        //     }
        //     i++; 
        // }
        // return max > count? max: count;

        //3. Using HashMap -> Time Complexity O(n)
        int len = nums.length;
        if(len == 0) return 0;
        int longestSeqLen = 0;

        HashMap<Integer, Boolean> exploredMap = new HashMap<>();

        for(int num: nums){
            exploredMap.put(num, Boolean.FALSE);
        }

        for(int num: nums){
            int currentSeqLen = 1;

            int nextNum = num+1;
            while(exploredMap.containsKey(nextNum) && exploredMap.get(nextNum) == false){
                currentSeqLen++;
                exploredMap.put(nextNum, Boolean.TRUE);
                nextNum++;
            }

            int prevNum = num-1;
            while(exploredMap.containsKey(prevNum) && exploredMap.get(prevNum) == false){
                currentSeqLen++;
                exploredMap.put(prevNum, Boolean.TRUE);
                prevNum--;
            }

            longestSeqLen = Math.max(longestSeqLen, currentSeqLen);
        }
        return longestSeqLen;
    }
}
