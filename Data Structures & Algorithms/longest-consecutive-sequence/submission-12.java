class Solution {
    // Approach: HashSet + "only start from true sequence starts."
    // A number is a true start only if (num - 1) is NOT in the set.
    // From each true start, count forward (num+1, num+2, ...) as long
    // as consecutive values exist. This ensures every number is only
    // ever counted once, as part of its own sequence's forward walk.
    //
    // TC: O(n) -> building the set is O(n); the main loop looks like
    //     nested loops, but each number is only ever visited a constant
    //     number of times total (once as a start-check, once max as
    //     part of exactly one sequence's forward count) -> O(n) overall
    // SC: O(n) -> HashSet stores all n numbers
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }

        int longest = 1;

        for (int num : numsSet) {
            // Only count from a true starting point (no predecessor in set).
            // Skips numbers that are mid-sequence -> avoids redundant re-counting.
            if (!numsSet.contains(num - 1)) {
                int length = 1;
                while (numsSet.contains(num + length)) {
                    length += 1;
                }
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}