package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by hanom on 26.01.2017.
 */
public class GroupContactHelperBase extends HelperBase {

  public GroupContactHelperBase(FirefoxDriver wd) {
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
    clickContact();
  }

  public void fillContactForm(ContactData contactData) {
    typeContact(By.name("firstname"), contactData.getFirstname());
    typeContact(By.name("lastname"), contactData.getLastname());
    typeContact(By.name("nickname"), contactData.getNickname());
    typeContact(By.name("address"), contactData.getAddress());
    typeContact(By.name("home"), contactData.getHomeTelephone());
    typeContact(By.name("mobile"), contactData.getMobileTelephone());
    typeContact(By.name("email"), contactData.getEmail());
    typeContact(By.name("address2"), contactData.getAddress2());
    typeContact(By.name("phone2"), contactData.getPhone2());
  }

  public void initContactCreation() {
    clickGroup(By.linkText("add new"));
  }

  public void initGroupModification() {
    clickGroup(By.name("edit"));
  }

  public void submitGroupModification() {
    clickGroup(By.name("update"));
  }
}
