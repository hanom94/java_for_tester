package ru.home.week1.mainTask1;

/**
 * Created by hanom on 22.01.2017.
 */
public class triangle {
  public static void main(String[] args) {
    double a = 1;
    double b = 2;
    double c = 3;

    perimeterTriangle at = new perimeterTriangle(1,2,3);

    System.out.println("[function] Перпиметр триугольника со сторонами a = " + a + " b = " + b + " c = " + c +
            " равняется: " + getAreaTriagleFunction(1, 2, 3));

    System.out.println("[method] Периметр триугольника со сторонами a = " + at.a + " b = " + at.b + " c = " + at.c +
            " равняется: " + at.getPerimetrTriagleMethod(at));
  }
  public static double getAreaTriagleFunction(double a, double b, double c){
    return a + b + c;
  }
}
