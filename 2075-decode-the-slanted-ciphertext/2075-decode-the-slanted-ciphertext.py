class Solution:
    def decodeCiphertext(self, encodedText: str, numRows: int) -> str:
        numCols = len(encodedText) // numRows
        result = []

        for diag in range(numCols):
            for r in range(numRows):
                col = diag + r
                if col < numCols:
                    result.append(encodedText[r * numCols + col])

        return ''.join(result).rstrip()