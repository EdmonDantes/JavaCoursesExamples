package task3;

public class Task3 {
    public static void main(String[] args) {
    }

    // 1
    static int factorial(int n) {
        if (n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    // 2
    static int sumDigits(int x) {
        if (x < 10) {
            return x;
        }

        return x % 10 + sumDigits(x / 10);
    }

    // 3
    static double arraySum(double[] x) {
        double min = x[0];
        double max = x[0];
        for (int i = 0; i < x.length; i++) { // or for (double y : x) { ... }
            if (x[i] > max) {
                max = x[i];
            }

            if (x[i] < min) {
                min = x[i];
            }
        }

        return min + max;
    }

    // 4
    static int arrayCount(int[] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) { // or for (int y : x) { ... }
            if (x[i] % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    // 5
    static int indexOf(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }

        return -1;
    }

    // 6
    static double averageColumn(double[][] x, int column) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i][column];
        }

        return sum / x.length;
    }

    // 7 - case 1
    static double diagonalAverageCase1(double[][] x) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i][i];
        }

        return sum / x.length;
    }

    // 7 - case 2
    static double diagonalAverageCase2(double[][] x) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i][count++];
        }

        return sum / count;
    }

    // 8
    static int countPositive(int[][] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            boolean isIncrease = true;
            for (int j = 1; j < x[i].length; j++) {
                if (x[i][j - 1] >= x[i][j]) {
                    isIncrease = false;
                    break;
                }
            }
            if (isIncrease) {
                count++;
            }
        }

        return count;
    }

    // 9
    static int[][] generateSpiral(int n) {
        int[][] result = new int[n][n];
        int startIndex = 0;
        int lastIndex = n - 1;
        int x = 0, y = 0;
        for (int i = 1; i < n * n + 1; i++) {
            result[y][x] = i;

            int increaseX = y == startIndex ? (x < lastIndex ? 1 : 0) : (y == lastIndex ? (x > startIndex ? -1 : 0) : 0);
            int increaseY = x == lastIndex ? (y < lastIndex ? 1 : 0) : (x == startIndex ? (y > startIndex ? -1 : 0) : 0);

            x += increaseX;
            y += increaseY;

            if (x == startIndex && y == startIndex) {
                x++;
                y++;
                startIndex++;
                lastIndex--;
            }
        }

        return result;
    }


    // 10 - case 1
    static double decimalSumCase1(double e) {
        int i = 1;
        double element;
        double sum = 0;

        do {
            element = 1.0 / i / i;
            sum += element;
            i++;
        } while (element > e);

        return sum;
    }

    // 10 - case 2
    static double decimalSumCase2(double e) {
        int i = 2;
        double element = 1;
        double sum = 0;

        while (element > e) {
            sum += element;
            element = 1.0 / i / i;
            i++;
        }

        return sum;
    }
}
