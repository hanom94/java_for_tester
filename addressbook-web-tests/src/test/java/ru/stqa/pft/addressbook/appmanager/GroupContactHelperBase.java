package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void returnToHomePage() {
    clickGroup(By.linkText("home"));
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

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
    type(By.name("work"), contactData.getPhone2());
    attach(By.name("photo"), contactData.getPhoto());

    if(creation) {
      if(contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addToGroup(int contact, int group) {
    selectGroupById(contact);
    selectValueInDropDown(By.name("to_group"), String.valueOf(group));
    clickContact(By.name("add"));
  }

  public void selectGroupPage(int group) {
    selectValueInDropDown(By.name("group"), String.valueOf(group));
  }

  public void deleteFromGroup(ContactInGroupData contact) {
    selectGroupPage(contact.getGroupId());
    selectContactById(contact.getContactId());
    wd.findElement(By.name("remove")).click();
  }

  public void findContact(int contact) {
    wd.findElement(By.tagName("input")).getAttribute(String.valueOf(contact));
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

  public void initContactModification(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void initContactMoreView(int id) {
    WebElement edit = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = edit.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }

  public void submitContactModification() {
    clickContact(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    clickContact(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modifyGroup(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void deleteGroup(GroupData deletedGroup) {
    selectGroupById(deletedGroup.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modifyContact(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void deleteContact(ContactData deletedContact) {
    selectContactById(deletedContact.getId());
    deleteSelectedContacts();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int GroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public int ContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  public Groups allGroup() {
    if(groupCache !=null){
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  private Contacts contactCache = null;

  public Contacts allContact() {
      if(contactCache !=null){
        return new Contacts(contactCache);
      }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String email = cells.get(4).getText();
      String address = cells.get(3).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname)
              .withLastname(lastname).withEmail(email).withAllPhones(allPhones).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditFrom(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String HomeTelephone = wd.findElement(By.name("home")).getAttribute("value");
    String MobileTelephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String Phone2 = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String address= wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomeTelephone(HomeTelephone).withMobileTelephone(MobileTelephone).withPhone2(Phone2).withEmail(email)
            .withAddress(address);
  }

  public ContactData infoFIOFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    //String fio = firstname.concat(" ").concat(lastname);
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work =  wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).
            withMobileTelephone(mobile).withHomeTelephone(home).withPhone2(work).withEmail(email);
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactMoreView(contact.getId());
    String allData = wd.findElement(By.xpath("//div[@id='content']")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withAllData(allData);
  }
}
