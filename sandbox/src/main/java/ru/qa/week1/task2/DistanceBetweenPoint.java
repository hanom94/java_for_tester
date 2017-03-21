package ru.qa.week1.task2;

public class DistanceBetweenPoint {

  public static void main(String[] args) {
    Point p1 = new Point(7, 7);
    Point p2 = new Point(14, 14);

    System.out.println("Расстояние между двумя точками: "
            + p1.x + " : " + p1.y + " и " + p2.x + " : " + p2.y + " = " + Point.distancePoint(p1,p2));
  }
}