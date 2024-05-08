// 7
public class Circle extends Shape {

    double r;
    Point center;

    // 7.1
    Circle(int color, Point center, double r) {
        super(ShapeType.CIRCLE, color);
        this.r = r;
        this.center = center;
    }

    // 7.2
    @Override
    double getPerimeter() {
        return Math.PI * r * r;
    }

    // 7.3
    @Override
    double getSquare() {
        return 2 * Math.PI * r;
    }
}