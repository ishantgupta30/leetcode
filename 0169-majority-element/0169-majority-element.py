class Solution:
    def majorityElement(self, nums: list[int]) -> int:
        candidate = None
        count = 0

        for num in nums:
            if count == 0:
                candidate = num   # pick new candidate

            if num == candidate:
                count += 1      # vote for candidate
            else:
                count -= 1      # cancel out a vote

        return candidate    