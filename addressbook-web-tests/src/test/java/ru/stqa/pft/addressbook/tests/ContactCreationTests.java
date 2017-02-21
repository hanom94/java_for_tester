package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withLastname("Иванов").withFirstname("Виктор")
            .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
            .withMobileTelephone("+380999999999").withPhone2("+380933333333")
            .withEmail("viktorxx@mail.ua").withAddress2(null)
            .withGroup("[none]")});
    list.add(new Object[]{new ContactData().withLastname("Иванов").withFirstname("Виктор")
            .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
            .withMobileTelephone("+380999999999").withPhone2("+380933333333")
            .withEmail("viktorxx@mail.ua").withAddress2(null)
            .withGroup("[none]")});
    list.add(new Object[]{new ContactData().withLastname("Иванов").withFirstname("Виктор")
            .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
            .withMobileTelephone("+380999999999").withPhone2("+380933333333")
            .withEmail("viktorxx@mail.ua").withAddress2(null)
            .withGroup("[none]")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    /*app.goTo().groupPage();
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }*/
      app.goTo().homePage();
      Contacts before = app.groupContact().allContact();
      app.groupContact().createContact(contact);
      assertThat(app.groupContact().ContactCount(), equalTo(before.size() + 1));
      Contacts after = app.groupContact().allContact();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    Contacts before = app.groupContact().allContact();
    ContactData contact = new ContactData().withLastname("Иванов'").withFirstname("Виктор")
            .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
            .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]");
    app.groupContact().createContact(contact);
    assertThat(app.groupContact().ContactCount(), equalTo(before.size() + 1));
    Contacts after = app.groupContact().allContact();
    assertThat(after, equalTo(before));
  }

}