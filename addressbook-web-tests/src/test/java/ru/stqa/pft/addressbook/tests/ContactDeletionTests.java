package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by hanom on 27.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.groupContact().groupList().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    if (app.groupContact().contactList().size() == 0){
      app.groupContact().createContact(new ContactData().withFirstname("Виктор").withLastname("Иванов")
              .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
              .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.groupContact().contactList();
    int index = before.size() - 1;
    app.groupContact().deleteContact(index);
    List<ContactData> after = app.groupContact().contactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
