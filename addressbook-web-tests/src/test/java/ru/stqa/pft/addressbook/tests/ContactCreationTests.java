package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupContactHelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    int before = app.getGroupContactHelperBase().getContactCount();
    app.getGroupContactHelperBase().createContact(new ContactData("Виктор", "Викторович",
            null, null, null,
            null, null,
            null, null, "test1"));
    app.getNavigationHelper().gotoHomePage();
    int after = app.getGroupContactHelperBase().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}