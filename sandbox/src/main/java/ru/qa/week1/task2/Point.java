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

  public double distancePoint (double x, double y){
    return Math.sqrt(Math.pow(this.x - x, 2)
            + Math.pow(this.y - y, 2));
  }

  double distance(Point p){
    return distancePoint(p.x, p.y);
  }

}
