package task4;

public class Task4 {
    public static void main(String[] args) {
    }

    // 1 -- case 1
    static int[] removeFrom(int[] arr, int x) {
        int newSize = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != x) {
                newSize++;
            }
        }

        if (newSize == arr.length) {
            return arr;
        }

        int[] newArr = new int[newSize];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] != x) {
                newArr[j++] = arr[i];
            }
        }

        return newArr;
    }

    // 1 -- case 2
    static int[] removeFrom2(int[] arr, int x) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != x) {
                arr[count++] = arr[i];
            }
        }

        if (count == arr.length) {
            return arr;
        }

        int[] result = new int[count];
        System.arraycopy(arr, 0, result, 0, count);
        return result;
    }

    // 2
    static int[][] matrixColumnReplace(int[][] x, int[] y) {
        for (int j = 0; j < x[0].length; j++) {
            int sum = 0;
            boolean hasNegativeNumber = false;
            for (int i = 0; i < x.length; i++) {
                sum += x[i][j];
                if (x[i][j] < 0) {
                    hasNegativeNumber = true;
                    break;
                }
            }

            if (hasNegativeNumber || sum % 2 == 0) {
                for (int i = 0; i < x.length; i++) {
                    x[i][j] = y[i];
                }
            }
        }
        return x;
    }

    // 3
    static int[][] multMatrix(int[][] x, int[][] y) {
        int[][] result = new int[x.length][];
        for (int i = 0; i < x.length; i++) {
            result[i] = new int[x[i].length];
            for (int j = 0; j < x[i].length; j++) {
                result[i][j] = x[i][j] * y[j][i];
            }
        }
        return result;
    }

    // 4
    static void numbers(int n) {
        if (n < 1) {
            return;
        }

        numbers(n - 1);

        for (int i = n; i > 0; i--) {
            System.out.print(i);
            System.out.print(" ");
        }

        System.out.println();
    }

    // 5
    static class Point {

        // 7
        static int count = 0;

        double x;
        double y;

        // 5.1
        Point(double x, double y) {
            this.x = x;
            this.y = y;
            count++;
        }

        // 5.2
        Point() {
            this.x = 0;
            this.y = 0;
            count++;
        }

        // 5.4
        static Point[] maxLength(Point[] arr) {
            Point[] result = new Point[]{arr[0], arr[1]};
            double maxLength = arr[0].length(arr[1]);
            for (int i = 2; i < arr.length; i++) {
                double lengthToFirst = arr[i].length(result[0]);
                double lengthToSecond = arr[i].length(result[1]);

                if (lengthToFirst > maxLength || lengthToSecond > maxLength) {
                    result[lengthToFirst > lengthToSecond ? 0 : 1] = arr[i];
                    maxLength = Math.max(lengthToFirst, lengthToSecond);
                }
            }
            return result;
        }

        // 5.3
        double length(Point another) {
            return Math.sqrt(Math.pow(x - another.x, 2) + Math.pow(y - another.y, 2));
        }
    }

    // 6
    static class Circle {
        double r;
        Point center;

        // 6.1
        Circle(Point center, double r) {
            this.center = center;
            this.r = r;
        }

        // 6.2
        public double getPerimeter() {
            return 2 * Math.PI * this.r;
        }

        // 6.3
        public double getSquare() {
            return Math.PI * Math.pow(this.r, 3);
        }
    }
}
