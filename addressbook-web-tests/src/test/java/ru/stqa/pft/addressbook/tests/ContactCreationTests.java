package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withLastname(split[0]).withFirstname(split[1]).withNickname(split[2])
                    .withAddress(split[3]).withHomeTelephone(split[4]).withMobileTelephone(split[4])
                    .withPhone2(split[5]).withEmail(split[6]).withAddress2(split[7]).withGroup(split[8])});
      line = reader.readLine();
    }
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