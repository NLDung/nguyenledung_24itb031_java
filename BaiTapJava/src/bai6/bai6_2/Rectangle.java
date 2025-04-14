package bai6.bai6_2;

public class Rectangle implements GametriObject {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public String toString() {
        return "Rectangle [width=" + width + ", height=" + height + "]";
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return (width + height) * 2;
    }
}