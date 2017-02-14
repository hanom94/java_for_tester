package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by hanom on 27.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    if (app.groupContact().allContact().size() == 0){
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
              .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.groupContact().allContact();
    ContactData deletedContact = before.iterator().next();
    app.groupContact().deleteContact(deletedContact);
    Set<ContactData> after = app.groupContact().allContact();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
