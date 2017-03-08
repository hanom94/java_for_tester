package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

/**
 * Modify by hanom on 07.03.2017.
 */
public class TestBase {

  protected static final ApplicationManager
          app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {

    //Инициализация ApplicationManager
    app.init();

    //Вызов метода для замены файла
    app.ftp().upload(new File("src/test/resources/config_inc.php"),
            "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    //Вызов метода restore
    app.ftp().restore("config_inc.php.bak", "config_inc.php");

    //Метод остановки приложения
    app.stop();
  }
}