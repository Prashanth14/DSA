class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = Integer.MIN_VALUE;
        for(int pile: piles){
           if(pile > maxPile){
            maxPile = pile;
           }
        }

        int left = 1, right = maxPile;

        while(left < right){
            long hours = 0;
            int mid = left + (right -left)/2;
            for(int pile: piles){
                hours += (long)Math.ceil((double)pile/mid);
            }

            if(hours <= h){
                right = mid;
            }else if(hours > h){
                left = mid + 1;
            }
        }
        return left;
    }
}
