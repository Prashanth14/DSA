class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        //brute force
        int len = temperatures.length;
        int[] res = new int[len];
        for(int i = 0; i <len; i++){
            for(int j = i+1; j <len; j++){
                if(temperatures[i] < temperatures[j]){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
}
