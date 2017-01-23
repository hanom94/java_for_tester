package ru.home.week1.mainTask1;

/**
 * Created by hanom on 22.01.2017.
 */
public class perimeterTriangle {
    public double a;
    public double b;
    public double c;

  public perimeterTriangle(double a, double b, double c){
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public static double getPerimetrTriagleFunction(double a, double b, double c){
        return a + b + c;
  }

  double getPerimetrTriagleMethod(perimeterTriangle at){
    return getPerimetrTriagleFunction(1, 2, 3);
  }
}


