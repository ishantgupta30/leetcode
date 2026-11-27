
class Solution {
    public ListNode swapPairs(ListNode head) {

        // Dummy node before the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {

            ListNode first = prev.next;
            ListNode second = first.next;

            // First node points to the remaining list
            first.next = second.next;

            // Second node points to first
            second.next = first;

            // Previous node points to second
            prev.next = second;

            // Move to the next pair
            prev = first;
        }

        return dummy.next;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna