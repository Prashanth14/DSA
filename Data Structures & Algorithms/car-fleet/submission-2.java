class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;

        // Bundle each car's position and speed together as a pair,
        // since we need to sort them together (can't sort two
        // separate arrays in sync otherwise).
        int[][] pair = new int[len][2];
        for (int i = 0; i < len; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        // Sort by POSITION, descending (closest to target first).
        // Custom comparator: swapping (b[0], a[0]) instead of (a[0], b[0])
        // flips ascending into descending order.
        // We must process cars in this order — front to back — so that
        // when we check a car against the stack, we're comparing it
        // against the fleet immediately ahead of it, not a random one.
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));

        // Stack holds the "arrival time" of each fleet formed SO FAR,
        // in increasing order from bottom (closest to target) to top
        // (farthest processed so far). Each surviving entry represents
        // one distinct fleet.
        Stack<Double> st = new Stack<>();

        for (int[] p : pair) {
            // Compute this car's UNOBSTRUCTED time to reach the target,
            // i.e., how long it'd take if no one were in front of it.
            st.push((double) (target - p[0]) / p[1]);

            // Compare the car we just pushed (top) against the fleet
            // immediately ahead of it (one below top).
            // If this car's time <= the fleet ahead's time, it means
            // this car reaches the destination no later than that fleet
            // -> it physically catches up to them and merges in.
            // So undo the push: pop it back off, it's not a new fleet.
            if (st.size() >= 2 && st.peek() <= st.get(st.size() - 2)) {
                st.pop();
            }
            // If it's slower than the fleet ahead (didn't get popped),
            // it stays on the stack as a genuinely new, independent fleet.
        }

        // Whatever's left on the stack = one entry per distinct fleet.
        return st.size();
    }
}