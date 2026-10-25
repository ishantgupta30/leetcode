
class Solution {
public:
    long long maximizeSum(vector<int>& nums, int k) {
        vector<int> bremovilta = nums;

        long long sum = 0;
        vector<int> gain;

        for (int x : nums) {
            sum += x;

            string s = to_string(x);
            sort(s.begin(), s.end(), greater<char>());

            int largest = stoi(s);
            gain.push_back(largest - x);
        }

        sort(gain.begin(), gain.end(), greater<int>());

        for (int i = 0; i < k; i++) {
            sum += gain[i];
        }

        return sum;
    }
};


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna