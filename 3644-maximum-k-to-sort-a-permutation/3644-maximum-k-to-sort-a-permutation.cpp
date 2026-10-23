
class Solution {
public:
    int sortPermutation(vector<int>& nums) {
        int k = -1;
        bool sorted = true;

        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] != i) {
                sorted = false;
                k &= nums[i];
                k &= i;
            }
        }

        return sorted ? 0 : k;
    }
};


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna