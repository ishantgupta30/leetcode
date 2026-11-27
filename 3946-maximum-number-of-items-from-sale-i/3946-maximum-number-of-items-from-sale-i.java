
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] dp = new int[budget + 1];

        int minPrice = Integer.MAX_VALUE;

        // Process every item type
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            minPrice = Math.min(minPrice, price);

            // Count purchased item + free items
            int count = 0;

            for (int[] other : items) {

                if (other[0] % factor == 0) {
                    count++;
                }
            }

            // 0/1 Knapsack: iterate backwards
            for (int j = budget; j >= price; j--) {

                dp[j] = Math.max(
                    dp[j],
                    dp[j - price] + count
                );
            }
        }

        // Use remaining budget to obtain more copies
        int answer = 0;

        for (int j = 0; j <= budget; j++) {

            int extra = (budget - j) / minPrice;

            answer = Math.max(
                answer,
                dp[j] + extra
            );
        }

        return answer;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna