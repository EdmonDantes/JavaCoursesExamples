package task5;

// 8
public class Triangle extends Shape {

    double a, b, c;

    // 8.1
    Triangle(int color, double a, double b, double c) {
        super(ShapeType.TRIANGLE, color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // 8.2
    @Override
    double getPerimeter() {
        return a + b + c;
    }

    // 8.3
    @Override
    double getSquare() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}