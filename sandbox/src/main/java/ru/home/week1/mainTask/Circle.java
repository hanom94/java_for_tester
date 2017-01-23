package ru.home.week1.mainTask;

public class Circle {

  public static void main(String[] args) {

    areaCircle c1 = new areaCircle(1);
    cCircle c2 =  new cCircle(1);

    System.out.println("[method] Площадь круга равняется: " + c1.areaOfCircleMethod(1));
    System.out.println("[function] Площадь круга равняется: " + areaOfCircleFunction(c1));

    System.out.println("[method] Длинна окружности равняется: " + c2.circumferenceSearchMethod(1));
    System.out.println("[function] Длинна окружности равняется: " + circumferenceOfCircleFunction(c2));
  }

  public static double areaOfCircleFunction(areaCircle c1) {
    return Math.PI * (c1.circleRadius * c1.circleRadius);
  }

  public static double circumferenceOfCircleFunction(cCircle c2) {
    return Math.PI * 2 * c2.radiusCircle;
  }
}
