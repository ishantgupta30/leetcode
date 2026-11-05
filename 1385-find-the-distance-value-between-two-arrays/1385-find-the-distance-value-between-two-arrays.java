
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        int count = 0;

        for (int i = 0; i < arr1.length; i++) {

            boolean valid = true;

            for (int j = 0; j < arr2.length; j++) {

                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna