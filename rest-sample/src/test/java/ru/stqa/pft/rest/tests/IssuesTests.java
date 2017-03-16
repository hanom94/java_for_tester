package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by hanom on 16.03.2017.
 */
public class IssuesTests extends TestBase {

  @Test
  public void testPreconditions() throws IOException {
    skipIfNotFixed(1);
    skipIfNotFixed(2);
    skipIfNotFixed(3);
  }
}
