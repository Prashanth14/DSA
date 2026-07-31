class Solution {
    // Approach: Boyer-Moore Voting Algorithm.
    // Treat every element as a "vote": if it matches the current
    // candidate, count++ (supports it); if not, count-- (opposes it).
    // When count hits 0, there's no standing candidate, so adopt
    // whatever element comes next as the new candidate.
    // Since the true majority appears more than n/2 times, it can
    // never be fully cancelled out by all other elements combined,
    // so whatever survives to the end IS the majority element.
    //
    // TC: O(n) -> single pass, one comparison per element
    // SC: O(1) -> only two variables (majorityEle, count), no
    //             extra array/map/sort needed
    public int majorityElement(int[] nums) {
        int majorityEle = 0; // current leading candidate
        int count = 0;       // net "votes" for majorityEle

        for (int num : nums) {
            // No standing candidate -> promote this element.
            if (count == 0) {
                majorityEle = num;
            }

            // Vote for or against the current candidate.
            if (majorityEle == num) {
                count += 1;
            } else {
                count -= 1;
            }
        }

        return majorityEle; // survivor = guaranteed majority element
    }
}