class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0, i = 0, j = people.length-1;

        while ( i <= j){
            int remaining = limit - people[j];
            j--;
            count++;
            if(remaining >= people[i]){
                i++;
            }

        }
        return count;
    }
}