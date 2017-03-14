package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Modify by hanom on 07.03.2017.
 */
public class ApplicationManager {

  private final Properties properties;

  //Что-бы никто к методу getDriver() не обратился случайно, делаем его private
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;

  //Создаем поле, которое будет содержать ссылку на помощника
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private AdministratorHelper administratorHelper;
  private JamesHelper jamesHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  //Делаем инициализацию драйвера останова с проверкой
  public void stop() {
    if (wd != null){
      wd.quit();
    }
  }

  //Инициализация помощника(конструирование) при каждом обращении (для того, что-бы открыть несколько сессий)
  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  //Возвращение обьекта типа RegistrationHelper
  //Реализовываем ленивую проверку
  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  //Метод возвращает объект типа FtpHelper
  public FtpHelper ftp(){
    if(ftp == null) {
      //в качестве параметра передается ссылка на ApplicationManager
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  //Возвращение обьекта типа AdministratorHelper
  public AdministratorHelper admin(){
    if (administratorHelper==null) {
      administratorHelper = new AdministratorHelper(this);
    }
    return administratorHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) { //Если драйвер не проинициализирован
      if(browser.equals(BrowserType.FIREFOX)){
        wd = new FirefoxDriver();
      } else if(browser.equals(BrowserType.CHROME)){
        wd = new ChromeDriver();
      } else if(browser.equals(BrowserType.IE)){
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public MailHelper mail(){
    if (mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james(){
    if (jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public SoapHelper soap(){
    if (soapHelper == null){
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
