class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        // Need at least 3 elements to form a triplet
        if (nums == null || len < 3) return new ArrayList<>();

        // Sort so we can use two pointers and skip duplicates easily
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            // Smallest number in remaining array is positive, so no triplet can sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate values for i to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = len - 1;

            // Two-pointer scan for the remaining two numbers
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // Skip duplicate values for left to avoid duplicate triplets
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (sum > 0) {
                    // Sum too large, shrink from the right
                    right--;
                } else {
                    // Sum too small, grow from the left
                    left++;
                }
            }
        }
        return result;
    }
}