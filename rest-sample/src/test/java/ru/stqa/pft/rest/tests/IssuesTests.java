package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by hanom on 16.03.2017.
 */
public class IssuesTests extends TestBase {

  @Test
  public void testPreconditions() throws IOException {
    //Тесты игнорируют актуальные баг-репорты (те, кторые не закрыты)
    skipIfNotFixed(1);
    skipIfNotFixed(2);
    skipIfNotFixed(3);

    //Тест для закрытых баг-репортов
    getIssueById(10);
  }
}
