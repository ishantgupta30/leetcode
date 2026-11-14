
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            int sum = 0;

            // Try subarrays of different lengths
            for (int j = i; j < n && j - i + 1 <= r; j++) {

                sum += nums.get(j);

                int len = j - i + 1;

                if (len >= l && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna