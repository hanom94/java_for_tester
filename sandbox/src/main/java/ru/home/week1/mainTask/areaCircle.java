package ru.home.week1.mainTask;

/**
 * Created by hanom on 22.01.2017.
 */
public class areaCircle {
  public double circleRadius = 3;

  public areaCircle(double circleRadius){
    this.circleRadius = circleRadius;
  }


  public static double areaOfCircleMethod(double circleRadius){
    return Math.PI * (circleRadius * circleRadius);
  }

  double circleRadius (areaCircle c1){
    return areaOfCircleMethod(5);
  }

}
