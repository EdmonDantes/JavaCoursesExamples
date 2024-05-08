public class Task {
    public static void main(String[] args) {
    }

    // 1
    static int customMod(double a, int b) {
        return (int) a % b;
    }

    // 2
    static double formula1(double x) {
        return Math.sqrt(Math.pow(x, 4) + Math.sqrt(Math.abs(x + 1)));
    }

    // 3
    static double formula2(double x) {
        return Math.log(Math.abs(Math.cos(x))) / Math.log(1 + Math.pow(x, 2));
    }

    // 4
    static double formula3(double x) {
        return Math.abs(Math.pow(x, 3) - Math.pow(x, 2)) - (7 * x) / (Math.pow(x, 3) - 15 * x);
    }

    // 5
    static int increaseEven(int x) {
        if (x % 2 == 1) {
            return 0;
        } else {
            return x * 2;
        }
    }

    // 6
    static double complexSun(int x, int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += Math.pow(x, i) / i;
        }
        return sum;
    }

    // 7
    static int monthDays(int m) {
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return 28;
        }
        return -1;
    }

    // 8 example 1
    static void sequenceCalc1(int a, int b) {
        for (int i = a; i <= b; i++) {
            if (i > 0 && i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    // 8 example 2
    static void sequenceCalc2(int a, int b) {
        for (int c = 2; c <= b; c += 2) {
            System.out.println(c);
        }
    }


    // 9 example with console
    static void reverse1(int number) {
        while (number != 0) {
            System.out.print(number % 10);
            number /= 10;
        }
    }

    // 9 example with variable
    static int reverse2(int number) {
        int result = 0;
        while (number != 0) {
            result = result * 10 + number % 10;
            number /= 10;
        }
        return result;
    }
}
