
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000;

        // Step 1: Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {

                int r = l + len - 1;
                cost[l][r] = INF;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) continue;

                    int changes = 0;

                    // Check each group
                    for (int offset = 0; offset < d; offset++) {

                        int left = l + offset;
                        int right = l + offset
                                  + ((len - 1 - offset) / d) * d;

                        // Check palindrome condition
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // Step 2: Dynamic Programming
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        // p = number of substrings
        for (int p = 1; p <= k; p++) {

            // i = number of characters used
            for (int i = 2 * p; i <= n; i++) {

                // t = starting position of last substring
                for (int t = 2 * (p - 1); t <= i - 2; t++) {

                    dp[i][p] = Math.min(
                        dp[i][p],
                        dp[t][p - 1] + cost[t][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}



// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna