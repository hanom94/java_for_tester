package ru.qa.week1.task2;

/**
 * Created by hanom on 20.01.2017.
 */
public class Point {
  public double x;
  public double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }

  public static double distancePoint (Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2)
            + Math.pow(p2.y - p1.y, 2));
  }
}
