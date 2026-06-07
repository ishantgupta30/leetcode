class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:            # negative numbers are never palindrome
            return False
        
        original = x         # ✅ save original before loop destroys x
        revnumber = 0
        while x:
            last_digit = x % 10
            x = x // 10
            revnumber = revnumber * 10 + last_digit
        
        return revnumber == original  # ✅ compare with original

# Synced seamlessly with LeetHub Pro
# Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
# Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna