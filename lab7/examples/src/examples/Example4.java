package examples;

// Абстрактный класс с абстрактным методом вычисления площади
abstract class Figure2 {

    double dim1;
    double dim2;

    Figure2(double a, double b) {
        dim1 = a;
        dim2 = b;
    }
    
    // Это абстрактный метод
    abstract double area();
}

class Rectangle2 extends Figure2 {

    Rectangle2(double a, double b) {
        super(a, b);
    }
    
    // Реализация абстрактного метода для четырехугольника
    double area() {
        System.out.println("Inside Area for Rectangle.");
        return dim1 * dim2;
    }
}

class Triangle2 extends Figure2 {

    Triangle2(double a, double b) {
        super(a, b);
    }
    
    // Реализация абстрактного метода для прямоугольного треугольника
    double area() {
        System.out.println("Inside Area for Triangle.");
        return dim1 * dim2 / 2;
    }
}

class Example4 {

    public static void main(String args[]) {
        // Figure f = new Figure(2, 2); // недопустимое объявление Figure
        Rectangle2 r = new Rectangle2(9, 5);
        Triangle2 t = new Triangle2(10, 8);

        Figure2 figref; // допустимое объявление, но объект не создается
        figref = r;
        System.out.println("Area is " + figref.area());

        figref = t;
        System.out.println("Area is " + figref.area());
    }
}
