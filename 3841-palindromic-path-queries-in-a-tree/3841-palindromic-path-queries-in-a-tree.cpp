
class Solution {
public:
    vector<bool> palindromePath(int n, vector<vector<int>>& edges,
                                string s, vector<string>& queries) {
        vector<vector<int>> adj(n);
        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        vector<int> tin(n), tout(n), depth(n), parent(n, -1);
        vector<vector<int>> up(LOG, vector<int>(n, -1));
        vector<int> pref(n);
        int timer = 0;

        // Iterative DFS: Euler tour and root-to-node XOR masks
        vector<int> it(n, 0), st = {0};
        parent[0] = 0;

        while (!st.empty()) {
            int u = st.back();

            if (it[u] == 0) {
                tin[u] = timer++;
                pref[u] = (u == 0 ? 0 : pref[parent[u]])
                          ^ (1 << (s[u] - 'a'));

                up[0][u] = parent[u];
                for (int k = 1; k < LOG; k++)
                    up[k][u] = up[k - 1][up[k - 1][u]];
            }

            if (it[u] < (int)adj[u].size()) {
                int v = adj[u][it[u]++];
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                st.push_back(v);
            } else {
                tout[u] = timer - 1;
                st.pop_back();
            }
        }

        auto lca = [&](int u, int v) {
            if (depth[u] < depth[v]) swap(u, v);

            int d = depth[u] - depth[v];
            for (int k = 0; k < LOG; k++)
                if (d & (1 << k)) u = up[k][u];

            if (u == v) return u;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][u] != up[k][v]) {
                    u = up[k][u];
                    v = up[k][v];
                }
            }
            return parent[u];
        };

        // Range XOR updates, point queries
        vector<int> bit(n + 2, 0);

        auto add = [&](int i, int val) {
            for (++i; i <= n + 1; i += i & -i)
                bit[i] ^= val;
        };

        auto pointQuery = [&](int i) {
            int res = 0;
            for (++i; i > 0; i -= i & -i)
                res ^= bit[i];
            return res;
        };

        for (int u = 0; u < n; u++) {
            add(tin[u], pref[u]);
            add(tout[u] + 1, pref[u]);
        }

        // Convert the difference representation into a proper
        // range-update / point-query Fenwick tree.
        fill(bit.begin(), bit.end(), 0);
        for (int u = 0; u < n; u++) {
            add(tin[u], 1 << (s[u] - 'a'));
            add(tout[u] + 1, 1 << (s[u] - 'a'));
        }

        vector<bool> ans;

        for (string& q : queries) {
            string type;
            int u;
            stringstream ss(q);
            ss >> type >> u;

            if (type == "update") {
                char c;
                ss >> c;

                int delta = (1 << (s[u] - 'a')) ^ (1 << (c - 'a'));
                add(tin[u], delta);
                add(tout[u] + 1, delta);
                s[u] = c;
            } else {
                int v;
                ss >> v;

                int w = lca(u, v);
                int pu = pointQuery(tin[u]);
                int pv = pointQuery(tin[v]);
                int pathMask = pu ^ pv ^ (1 << (s[w] - 'a'));

                ans.push_back((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }
};


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna