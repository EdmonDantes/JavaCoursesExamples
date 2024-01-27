package task5;

// 3
public class Vector extends Line {

    // 3.1
    Vector(Point pos1, Point pos2) {
        super(pos1, pos2);
    }

    // 3.2
    Vector(Line line) {
        super(line.pos1, line.pos2);
    }

    // 3.3
    Vector startFrom(Point pos1) {
        double xDiff = this.pos2.x - this.pos1.x;
        double yDiff = this.pos2.y - this.pos1.y;

        return new Vector(pos1, new Point(pos1.x + xDiff, pos1.y + yDiff));
    }

    // 3.4
    Vector scalarSum(Vector another) {
        Vector movedVector = another.startFrom(pos2);

        return new Vector(pos1, movedVector.pos2);
    }

    // 3.5
    double scalarMult(Vector another) {
        Vector firstMovedVector = startFrom(new Point(0, 0));
        Vector secondMovedVector = startFrom(new Point(0, 0));

        Point a = firstMovedVector.pos2;
        Point b = secondMovedVector.pos2;


        return a.x * b.x + a.y * b.y;
    }

}