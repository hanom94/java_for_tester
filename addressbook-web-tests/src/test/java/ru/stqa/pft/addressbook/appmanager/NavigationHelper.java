package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by hanom on 26.01.2017.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
      clickGroup(By.linkText("groups"));
  }

  public void gotoHomePage() {
    clickContact(By.linkText("home"));
  }
}
