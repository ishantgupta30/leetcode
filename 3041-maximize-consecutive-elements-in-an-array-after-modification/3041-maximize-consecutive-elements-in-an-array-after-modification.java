import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : nums) {
            // x -> x+1 (uses old dp[x], so do this first)
            int up = dp.getOrDefault(x, 0) + 1;
            // x stays x
            int stay = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), up));
            dp.put(x, Math.max(dp.getOrDefault(x, 0), stay));

            ans = Math.max(ans, Math.max(up, stay));
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna