package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by hanom on 26.01.2017.
 */
public class GroupContactHelperBase extends HelperBase {

  public GroupContactHelperBase(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    clickGroup(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    clickGroup(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    clickGroup(By.name("new"));
  }

  public void deleteSelectedGroups() {
    clickGroup(By.name("delete"));
  }

  public void selectGroup() {
    clickGroup(By.name("selected[]"));
  }

  public void submitContactCreation() {
    clickContact(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomeTelephone());
    type(By.name("mobile"), contactData.getMobileTelephone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());

    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    clickContact(By.linkText("add new"));
  }

  public void initGroupModification() {
    clickGroup(By.name("edit"));
  }

  public void submitGroupModification() {
    clickGroup(By.name("update"));
  }

  public void initContactModification() {
    clickContact(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    clickContact(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void selectContact() {
    clickContact(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    clickContact(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }
}
