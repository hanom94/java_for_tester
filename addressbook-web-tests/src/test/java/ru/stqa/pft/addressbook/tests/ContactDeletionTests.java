package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by hanom on 27.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.groupContact().allGroup().size() == 0) {
        app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
      }
      app.goTo().homePage();
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/stru.png");
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333").withPhoto(photo)
              .withEmail("viktorxx@mail.ua").withAddress2(null).inGroups(groups.iterator().next()));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.groupContact().deleteContact(deletedContact.removeGroups(groups.iterator().next()));
    assertThat(app.groupContact().ContactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

    verifyContactListInUI();
  }
}
