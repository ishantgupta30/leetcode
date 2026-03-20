class Solution:
    def generateParenthesis(self, n: int):
        result = []

        def backtrack(curr, open_count, close_count):
            # If the current string is complete
            if len(curr) == 2 * n:
                result.append(curr)
                return
            
            # Add '(' if we still have some left
            if open_count < n:
                backtrack(curr + "(", open_count + 1, close_count)
            
            # Add ')' if it won't make it invalid
            if close_count < open_count:
                backtrack(curr + ")", open_count, close_count + 1)
        
        backtrack("", 0, 0)
        return result