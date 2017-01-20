package ru.qa.week1.task2;

public class DistanceBetweenPoint {

  public static void main(String[] args) {
    Point p1 = new Point(7, 7);
    Point p2 = new Point(14,14);

    //Использование функции для вычисления
    System.out.println("Расстояние между двумя точками: "
            + p1.x + " : " + p1.y + " и " + p2.x + " : " + p2.y + " = " + distance(p1, p2));

    //Использование метода для вычисления
    System.out.println("Расстояние между двумя точками: "
            + p1.x + " : " + p1.y + " и " + p2.x + " : " + p2.y + " = " + p1.distance(p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }
}