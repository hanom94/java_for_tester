package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanom on 26.01.2017.
 */
public class ApplicationManager {

  private final Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupContactHelperBase groupContactHelperBase;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if("".equals(properties.getProperty("selenium.server"))) {
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupContactHelperBase = new GroupContactHelperBase(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupContactHelperBase groupContact() {
    return groupContactHelperBase;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public DbHelper db(){
    return dbHelper;
  }

  public byte[]takeScreenshot(){
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
