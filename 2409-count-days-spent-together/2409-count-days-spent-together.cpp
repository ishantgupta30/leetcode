

class Solution {
public:
    int countDaysTogether(string arriveAlice, string leaveAlice,
                          string arriveBob, string leaveBob) {

        int days[] = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        auto getDay = [&](string date) {
            int month = stoi(date.substr(0, 2));
            int day = stoi(date.substr(3, 2));

            int total = day;

            for (int i = 0; i < month - 1; i++) {
                total += days[i];
            }

            return total;
        };

        int aStart = getDay(arriveAlice);
        int aEnd = getDay(leaveAlice);
        int bStart = getDay(arriveBob);
        int bEnd = getDay(leaveBob);

        int start = max(aStart, bStart);
        int end = min(aEnd, bEnd);

        if (start > end) {
            return 0;
        }

        return end - start + 1;
    }
};


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna