// 6
public class Rectangle extends Shape {

    protected double a, b;

    // 6.1
    Rectangle(int color, double a, double b) {
        super(ShapeType.RECTANGLE, color);
        this.a = a;
        this.b = b;
    }

    // 6.2
    @Override
    double getPerimeter() {
        return (a + b) * 2;
    }

    // 6.3
    @Override
    double getSquare() {
        return a * b;
    }
}