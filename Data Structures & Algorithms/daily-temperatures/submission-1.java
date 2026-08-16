class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Brute force approach: for each day, scan forward day by day
        // until we find a strictly warmer temperature.

        int len = temperatures.length;

        // res[i] = number of days to wait after day i for a warmer
        // temperature. Defaults to 0 (Java int arrays auto-init to 0),
        // which correctly represents "no warmer day ever comes."
        int[] res = new int[len];

        // Outer loop: fix the "current day" we're computing the answer for.
        for (int i = 0; i < len; i++) {

            // Inner loop: look ahead from day i+1 onward for the FIRST
            // day with a strictly higher temperature.
            for (int j = i + 1; j < len; j++) {

                if (temperatures[i] < temperatures[j]) {
                    // Found the first warmer day -> record the gap in days.
                    res[i] = j - i;
                    break; // stop scanning further, we only need the FIRST warmer day
                }
                // If not warmer, keep scanning forward (res[i] stays 0
                // for now, will remain 0 if no warmer day is ever found).
            }
        }

        return res;
    }
}