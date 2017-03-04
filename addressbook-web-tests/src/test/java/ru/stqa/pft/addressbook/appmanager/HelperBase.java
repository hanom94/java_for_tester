package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

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
    if (text != null){
      String existingText = wd.findElement(locator).getAttribute("value");
      if(! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public void selectValueInDropDown(By locator, String value){
    Select dropdown = new Select(wd.findElement(locator));
    dropdown.selectByValue(value);
  }

  protected void attach (By locator, File file) {
    if (file != null){
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  protected void clickContact(By locator) {
    wd.findElement(locator).click();
  }

  /*protected void typeContact(By locator, String text) {
    if(text != null) {
      wd.findElement(locator).clear();
      wd.findElement(locator).sendKeys(text);
    }
  }*/
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }

  public boolean isElementNotPresent(By locator) {
    try {
      wd.findElement(locator);
      return false;
    } catch (NoSuchElementException ex){
      return true;
    }
  }
}
