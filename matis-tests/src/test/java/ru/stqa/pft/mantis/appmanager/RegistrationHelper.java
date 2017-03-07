package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by hanom on 07.03.2017.
 */
public class RegistrationHelper {
  private final ApplicationManager app;

  //Драйвер
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;

    //Ссылка на драйвер берется у ApplicationManager
    //Меняем на ленивую инициализацию, инициализация в момент первого обращения
    wd = app.getDriver();

  }

  //Открываем нужную страницу
  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
