import java.util.Random;

public class Main {

    private static int[] letterCounter;

    public static void main(String[] args) {

        String[] strs = new String[]{
                "Debit card", "Bad credit2",
                "Slot machines", "Cash lost in 'em",
                "School master", "The classroom",
                "Eleven plus two", "Twelve plus one3",
                "Dormitory", "Dirty room4",
                "Punishment", "Nine Thumps5",
                "Desperation", "A rope ends it6",
                "The Morse code", "Here come dots",
                "Snooze alarms", "Alas! No more Zs7",
                "A decimal point", "I'm a dot in place",
                "Astronomer", "Moon starer8",
                "Fir cones", "Conifers",
                "The eyes", "They see9",
                "Payment received", "Every cent paid me10",
                "Conversation", "Voices rant on",
                "The public art galleries", "Large picture halls, I bet",
                "Election results", "Lies – let's recount11",
                "Halley's Comet", "Shall yet come12",
                "The Hurricanes", "These churn air13"
        };
        Random random = new Random();
        int count = 0;
        long start = System.currentTimeMillis();
        for (int dd = 0; dd < 100000; dd++) {
            for (int i = 0; i < strs.length; i += 2) {
                if (!isAnagram(strs[i], strs[i + 1])) count++;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

        count = 0;
        for (int i = 0; i < strs.length; i += 2) {
            if (isAnagram(strs[i], strs[i + 1] + (char) (random.nextInt(26) + 'a'))) count++;
        }


        for (int i = 0; i < strs.length; i += 2) {
            if (isAnagram(strs[i], strs[i + 1] + (char) (random.nextInt(26) + 'a'))) count++;
        }

        if (count == 0)
            System.err.println("successful!");
        else
            System.err.println("Failed " + count + " times out of " + strs.length);
    }

    private static boolean isAnagram(String first, String second) {
        letterCounter = new int[26];
        int i = first.length() > second.length() ? first.length() : second.length();
        while (--i >= 0) {
            if (i < first.length()) {
                checkLetterFor(first, i, -1);
            }
            if (i < second.length()) {
                checkLetterFor(second, i, 1);
            }
        }
        // fail fast;
        for (int count : letterCounter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private static void checkLetterFor(String first, int i, int step) {
        int theLetter = first.charAt(i);
        if (isALetter(theLetter)) {
            letterCounter[(theLetter & 31) - 1] += step;
        }
    }

    private static boolean isALetter(int candidate) {

        return candidate > 64 && (candidate & 31) < 27;
    }

}
