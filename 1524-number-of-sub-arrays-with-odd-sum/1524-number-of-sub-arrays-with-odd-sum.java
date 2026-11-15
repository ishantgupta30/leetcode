class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int odd = 0;      // prefix sums seen so far with odd parity
        int even = 1;     // empty prefix (sum 0) counts as even
        int prefix = 0;   // current prefix parity (0 = even, 1 = odd)
        long result = 0;

        for (int x : arr) {
            prefix = (prefix + x) & 1;
            if (prefix == 1) {
                result += even;   // odd - even = odd
                odd++;
            } else {
                result += odd;    // even - odd = odd
                even++;
            }
            result %= MOD;
        }
        return (int) result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna