class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& mat) {

        int m = mat.size();
        int n = mat[0].size();

        map<int, vector<int>> mp;

        vector<int> result;

        // Group elements according to i + j
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                mp[i + j].push_back(mat[i][j]);

            }
        }

        bool flip = true;

        // Go through each diagonal
        for (auto& it : mp) {

            if (flip) {
                reverse(it.second.begin(), it.second.end());
            }

            // Put diagonal elements into result
            for (int x : it.second) {
                result.push_back(x);
            }

            // Change direction for next diagonal
            flip = !flip;
        }

        return result;
    }
};

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna