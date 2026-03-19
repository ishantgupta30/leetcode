class Solution:
    def romanToInt(self, s: str) -> int:
        map = {
            'I': 1,   'V': 5,
            'X': 10,  'L': 50,
            'C': 100, 'D': 500,
            'M': 1000
        }

        result = 0

        for i in range(len(s)):
            curr = map[s[i]]
            next = map[s[i + 1]] if i + 1 < len(s) else 0

            # subtractive pair if current < next
            if curr < next:
                result -= curr
            else:
                result += curr

        return result