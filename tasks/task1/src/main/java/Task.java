public class Task {

    public static void main(String[] args) {
        // 1
        long longVar = 1;
        System.out.println(longVar <= 13 && longVar >= 9);

        // 2
        System.out.println(0b11100110101001);

        // 3
        System.out.println(042352610330L);

        // 4
        System.out.println(longVar / 10 % 10);

        // 5
        long secondVar = 1;
        System.out.println(longVar + secondVar);

        // 6
        double x = 0.6;
        System.out.println(0.125 * x * x * x - 6);

        // 7
        double y = 0.7;
        System.out.println((16 - x) / y / y);

        // 8
        System.out.println(longVar >= 500 && longVar <= 700 || longVar >= 120 && longVar <= 300);
    }

}
