package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Modify by hanom on 07.03.2017.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

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

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      if (isIssueOpen(issueId)) {
        throw new SkipException("Ignored because of issue " + issueId);
      }
    }
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = app.soap().getMantisConnect();
    IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
            BigInteger.valueOf(issueId));
    return !issue.getStatus().getName().equals("closed");
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p));
  }
}