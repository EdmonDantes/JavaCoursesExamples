package task5;

public class Point {

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