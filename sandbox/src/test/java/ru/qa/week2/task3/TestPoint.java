package ru.qa.week2.task3;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.week1.task2.Point;

/**
 * Created by hanom on 24.01.2017.
 */
public class TestPoint {

  @Test
  public void testArea(){
    Point p1 = new Point(7,7);
    Point p2 = new Point(14,14);
    Assert.assertEquals(Point.distancePoint(p1,p2), 9.899494936611665);

    Point p3 = new Point(-2,8);
    Point p4 = new Point(9,-14);
    Assert.assertEquals(Point.distancePoint(p3, p4), 24.596747752497688);

    Point p5 = new Point(-105,55);
    Point p6 = new Point(55,-125);
    Assert.assertEquals(Point.distancePoint(p5, p6), 240.8318915758459);
  }
}
