class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int len = people.length;
        int maxWeightPerson = Arrays.stream(people).max().getAsInt();

        int[] freq = new int[maxWeightPerson + 1];

        for(int p : people){
            freq[p] += 1;
        }

        int idx = 0, i =1;
        while(idx < len){
            while(freq[i] == 0){
                i++;
            }
            people[idx++] = i;
            freq[i]--;
        }

        int left  = 0, right = len-1;
        int minBoatsToRescue = 0;

        while(left <= right){
            int remain = limit - people[right];
            minBoatsToRescue++;
            right--;

            if(left <= right && people[left] <= remain){
                left++;
            }
        }

        return minBoatsToRescue;
    }
}