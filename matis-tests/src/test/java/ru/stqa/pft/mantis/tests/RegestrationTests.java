package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by hanom on 07.03.2017.
 */
public class RegestrationTests extends TestBase{

  @Test
  public void testRegostration(){
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
