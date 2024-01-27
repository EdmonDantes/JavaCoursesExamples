package task8;

public class Task8 {

    // 4
    public static boolean isPalindrom(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 5
    public static int countSubString(String x, String y) {
        int count = 0;
        for (int i = 0; i < x.length(); i++) {
            boolean founded = true;
            for (int j = 0; j < y.length(); j++) {
                if (y.charAt(j) != x.charAt(i)) {
                    founded = false;
                    break;
                }
            }

            if (founded) {
                count++;
            }
        }
        return count;
    }

    // 6
    public static String rps(String p1, String p2) {
        if (p1.equals(p2)) {
            return "Draw!";
        }

        boolean wonFirst = false;

        switch (p1) {
            case "scissors":
                wonFirst = p2.equals("paper");
                break;
            case "paper":
                wonFirst = p2.equals("rock");
                break;
            case "rock":
                wonFirst = p2.equals("scissors");
                break;
        }

        return "Player " + (wonFirst ? 1 : 2) + " won!";
    }

    // 7
    public static int[] Solve(String word) {
        int[] result = new int[4];

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = -1;
            if (Character.isUpperCase(ch)) {
                index = 0;
            } else if (Character.isLowerCase(ch)) {
                index = 1;
            } else if (Character.isDigit(ch)) {
                index = 2;
            } else {
                index = 3;
            }
            result[index]++;
        }

        return result;
    }

    // 8
    public static boolean CheckIfFlush(String[] cards) {
        for (int i = 1; i < cards.length; i++) {
            String card1 = cards[i];
            String card2 = cards[i - 1];
            if (card1.charAt(card1.length() - 1) != card2.charAt(card2.length() - 1)) {
                return false;
            }
        }
        return true;
    }

}

