package ru.home.week1.mainTask;

/**
 * Created by hanom on 22.01.2017.
 */
public class cCircle {
  public double radiusCircle;

  public cCircle(double radiusCircle) {
    this.radiusCircle = radiusCircle;
  }

  public static double circumferenceSearchMethod(double radiusCircle) {
    return Math.PI * 2 * radiusCircle;
  }

  double radiusCircle(cCircle c2) {
    return circumferenceSearchMethod(5);
  }
}
