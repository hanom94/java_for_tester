package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;

/**
 * Created by hanom on 03.03.2017.
 */
public class ContactDeleteInGroupTests extends TestBase {

  /*@BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
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
  }*/

  @Test
  public static void testContactDeleteInGroup() {
    ContactsInTheGroup contactsInTheGroup = app.db().contactingInGroup();
    ContactInGroupData contactInGroup = contactsInTheGroup.iterator().next();
    app.goTo().homePage();
    app.groupContact().deleteFromGroup(contactInGroup);
    app.goTo().homePage();
    app.groupContact().selectGroupPage(contactInGroup.getGroupId());
    app.groupContact().isElementNotPresent(By.id(String.valueOf(contactInGroup.getContactId())));
  }
}
