package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupContactHelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getGroupContactHelperBase().getContactList();
    app.getGroupContactHelperBase().createContact(new ContactData("Виктор","Иванов",
            null, null, null,
            null, null,
            null, null, "test1"));
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getGroupContactHelperBase().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}