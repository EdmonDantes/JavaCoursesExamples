package task5;

// 2
public class Line {

    Point pos1, pos2;

    // 2.1
    Line(Point pos1, Point pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    static double lineTan(Line line) {
        if (line.pos1.y == line.pos2.y) {
            return 0;
        } else if (line.pos1.x == line.pos2.x) {
            return 2;
        } else {
            double y = Math.abs(line.pos1.y - line.pos2.y);
            double x = Math.abs(line.pos1.x - line.pos2.x);
            return Math.tan(y / x);
        }
    }

    static boolean isCross(Point a, Point b, Point c, Point d) {
        return (isBetween(a.x, c.x, b.x) && isBetween(a.x, d.x, b.x) || isBetween(c.x, a.x, d.x) && isBetween(c.x, b.x, d.x))
                && (isBetween(a.y, c.y, b.y) && isBetween(a.y, d.y, b.y) || isBetween(c.y, a.y, d.y) && isBetween(c.y, b.y, d.y));
    }

    static boolean isBetween(double left, double x, double right) {
        return x >= left && x <= right;
    }

    // 2.2
    double length() {
        return pos1.length(pos2);
    }

    // 2.3
    boolean isCross(Line line) {
        return isCross(pos1, pos2, line.pos1, line.pos2);
    }

    // 2.4
    boolean isParallel(Line another) {
        return !isCross(another) && lineTan(this) == lineTan(another);
    }


}