package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by hanom on 26.01.2017.
 */
public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void clickGroup(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    typeContact(locator, text);
  }

  protected void clickContact(By locator) {
    wd.findElement(locator).click();
  }

  protected void typeContact(By locator, String text) {
    clickGroup(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
