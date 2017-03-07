package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by hanom on 07.03.2017.
 */
public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {

    //Создаем новую сессию
    HttpSession session = app.newSession();

    //Проверка "залогинился" ли пользователь(появился нужный текст)
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoginInAs("administrator"));
  }
}
