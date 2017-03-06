package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;


import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by hanom on 03.03.2017.
 */
public class ContactDeleteInGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
      app.goTo().homePage();
    }
    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.goTo().homePage();
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/stru.png");
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333").withPhoto(photo)
              .withEmail("viktorxx@mail.ua").withAddress2(null).inGroups(groups.iterator().next()));
      app.goTo().homePage();
    }
    Groups groups = app.db().groups();
    GroupData group  = groups.iterator().next();
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    app.goTo().homePage();
    app.groupContact().addToGroup(contact.getId(), group.getId());
  }

  @Test
  public static void testContactDeleteInGroup() {
    ContactsInTheGroup contactsInTheGroup = app.db().contactingInGroup();
    ContactInGroupData contactInGroup = contactsInTheGroup.iterator().next();
    app.goTo().homePage();
    app.groupContact().deleteFromGroup(contactInGroup);
    app.goTo().homePage();
    app.groupContact().selectGroupPage(contactInGroup.getGroupId());
    app.groupContact().checkUIContactIsDelete(contactInGroup);
    app.groupContact().checkDBContactIsDelete(contactInGroup);
  }
}
