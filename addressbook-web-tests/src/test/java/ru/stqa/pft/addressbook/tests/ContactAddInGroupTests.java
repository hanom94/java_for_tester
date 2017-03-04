package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

/**
 * Created by hanom on 03.03.2017.
 */
public class ContactAddInGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    if(app.db().contacts().size() == 0){
      app.goTo().homePage();
      File photo = new File("src/test/resources/stru.png");
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333").withPhoto(photo)
              .withEmail("viktorxx@mail.ua").withAddress2(null));
    }
  }

  @Test
  public static void testContactAddInGroup(){
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    app.goTo().homePage();
    ContactData contact = contacts.iterator().next();
    GroupData group = groups.iterator().next();
    app.goTo().homePage();
    app.groupContact().addToGroup(contact.getId(), group.getId());
    app.goTo().homePage();
    app.groupContact().selectGroupPage(group.getId());
    app.groupContact().findContact(contact.getId());
  }
}
