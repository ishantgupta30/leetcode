class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        cols = [0] * len(grid[0])
        ans = 0

        for row in grid:
            row_sum = 0
            for j, val in enumerate(row):
                cols[j] += val       # extend column sum down to current row
                row_sum += cols[j]   # running sum = submatrix (0,0) → (i,j)
                if row_sum <= k:
                    ans += 1

        return ans