from collections import Counter

class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        # Even-indexed chars must be anagrams of each other
        even_s1 = Counter(s1[i] for i in range(0, len(s1), 2))
        even_s2 = Counter(s2[i] for i in range(0, len(s2), 2))

        # Odd-indexed chars must be anagrams of each other
        odd_s1 = Counter(s1[i] for i in range(1, len(s1), 2))
        odd_s2 = Counter(s2[i] for i in range(1, len(s2), 2))

        return even_s1 == even_s2 and odd_s1 == odd_s2