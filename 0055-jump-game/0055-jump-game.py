class Solution:
    def canJump(self, nums: list[int]) -> bool:
        max_reach = 0

        for i in range(len(nums)):
            if i > max_reach:        # Can't reach this index
                return False
            max_reach = max(max_reach, i + nums[i])  # Update farthest reach

        return True