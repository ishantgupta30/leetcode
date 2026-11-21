
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Step 1: Build the original tree
        int[] head = new int[n];
        java.util.Arrays.fill(head, -1);

        int[] to = new int[n - 1];
        int[] next = new int[n - 1];

        int edge = 0;

        for (int i = 1; i < n; i++) {
            int p = parent[i];

            to[edge] = i;
            next[edge] = head[p];
            head[p] = edge++;
        }

        // Step 2: Find the new parent of every node
        int[] newParent = parent.clone();

        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        int[] previous = new int[n];

        // Iterative DFS: ~node represents exit event
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int event = stack[--top];

            // Exit event: restore previous character position
            if (event < 0) {

                int node = ~event;
                int c = s.charAt(node) - 'a';

                last[c] = previous[node];

                continue;
            }

            int node = event;
            int c = s.charAt(node) - 'a';

            // Save previous occurrence
            previous[node] = last[c];

            // Find closest ancestor with same character
            if (last[c] != -1) {
                newParent[node] = last[c];
            }

            // Update latest occurrence
            last[c] = node;

            // Push exit event
            stack[top++] = ~node;

            // Visit original children
            for (int e = head[node]; e != -1; e = next[e]) {
                stack[top++] = to[e];
            }
        }

        // Step 3: Build the modified tree
        int[] newHead = new int[n];
        java.util.Arrays.fill(newHead, -1);

        int[] newTo = new int[n - 1];
        int[] newNext = new int[n - 1];

        edge = 0;

        for (int i = 1; i < n; i++) {

            int p = newParent[i];

            newTo[edge] = i;
            newNext[edge] = newHead[p];
            newHead[p] = edge++;
        }

        // Step 4: Get traversal order of modified tree
        int[] order = new int[n];
        int[] st = new int[n];

        top = 0;
        int count = 0;

        st[top++] = 0;

        while (top > 0) {

            int node = st[--top];

            order[count++] = node;

            for (int e = newHead[node]; e != -1; e = newNext[e]) {
                st[top++] = newTo[e];
            }
        }

        // Step 5: Calculate subtree sizes
        int[] answer = new int[n];

        java.util.Arrays.fill(answer, 1);

        // Process children before parents
        for (int i = n - 1; i > 0; i--) {

            int node = order[i];

            answer[newParent[node]] += answer[node];
        }

        return answer;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna