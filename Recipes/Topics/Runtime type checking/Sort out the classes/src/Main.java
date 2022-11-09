import java.util.ArrayList;
import java.util.List;

class Sort {
    public static void sortShapes(Shape[] array,
                                  List<Shape> shapes,
                                  List<Polygon> polygons,
                                  List<Square> squares,
                                  List<Circle> circles) {
        // write your code here
        for(Shape s : array) {

            String name = s.getClass().getSimpleName();
//            System.out.println(name);
            if(name.equals("Shape")) {
                shapes.add(s);
            }

            if(name.equals("Polygon")) {
                polygons.add((Polygon) s);
            }

            if(name.equals("Square")) {
                squares.add((Square) s);
            }

            if(name.equals("Circle")) {
                circles.add((Circle) s);
            }
        }
    }
}

//Don't change classes below
class Shape { }
class Polygon extends Shape { }
class Square extends Polygon { }
class Circle extends Shape { }

//public class Main {
//    public static void main(String[] args) {
//        Shape s1 = new Polygon();
//        Shape s2 = new Square();
//        Shape s = new Shape();
//        Shape s3 = new Circle();
//
//        Shape[] shapeArray = {s1, s2, s3, s};
//
//        List<Shape> shapes = new ArrayList<>();
//        List<Polygon> polygons = new ArrayList<>();
//        List<Square> squares = new ArrayList<>();
//        List<Circle> circles = new ArrayList<>();
//
//        Sort.sortShapes(shapeArray, shapes, polygons,squares, circles);
//        System.out.println(shapes);
//        System.out.println(polygons);
//        System.out.println(squares);
//        System.out.println(circles);
//    }
//}