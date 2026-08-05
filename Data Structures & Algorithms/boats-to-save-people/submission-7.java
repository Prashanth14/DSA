class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int len = people.length;

        int left = 0, right = len -1;
        int minBoats = 0;

        while(left <= right){
            int remain = 0;
            remain = limit - people[right];
            right--;
            minBoats += 1;

            if( left <= right && remain >= people[left]){
                left++;
            }
        }
        return minBoats;
    }
}