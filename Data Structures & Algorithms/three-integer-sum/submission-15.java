class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort so equal values sit next to each other — required for duplicate skipping below
        Arrays.sort(nums);

        // Brute force: check every triplet (i, j, k) with i < j < k
        List<List<Integer>> result = new ArrayList<>();

        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // Skip duplicate values for i to avoid duplicate triplets in the result
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < len; j++) {
                // Skip duplicate values for j (only compare within the current i's range)
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                for (int k = j + 1; k < len; k++) {
                    // Skip duplicate values for k (only compare within the current j's range)
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;

                    // Found a valid triplet summing to zero
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }
}