class Solution {
    public int longestConsecutive(int[] nums) {
        //1.using sorting, set and List Time Complexity is O(n*logn)
        int len = nums.length;
        if(len == 0) return 0;
        //To Store unique elements in the set
        HashSet<Integer> set = new HashSet<>();

        for(int num: nums){
            set.add(num);
        }

        //Convert set to List and sort list
        ArrayList<Integer> uniqueList = new ArrayList<>(set);
        Collections.sort(uniqueList);

        int lsize = uniqueList.size();
        int count = 1;
        int i = 0;
        int max = 0;
        while(i<lsize-1){
            if((uniqueList.get(i+1) - uniqueList.get(i)) == 1){
                count++;
                 i++;
            }else if((uniqueList.get(i+1) - uniqueList.get(i)) > 1){

                max = max > count? max: count;
                count = 1;
                i++;
            }
        }
        return max > count? max: count;
    }
}
