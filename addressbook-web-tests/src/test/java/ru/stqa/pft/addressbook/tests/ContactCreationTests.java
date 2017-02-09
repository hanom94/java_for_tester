package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupContactHelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getGroupContactHelperBase().getContactList();
    ContactData contact = new ContactData("Виктор", "Иванов",
            null, null, null,
            null, null,
            null, null, "test1");
    app.getGroupContactHelperBase().createContact(contact);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getGroupContactHelperBase().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}