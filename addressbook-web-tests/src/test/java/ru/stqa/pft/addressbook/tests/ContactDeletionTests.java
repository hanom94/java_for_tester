package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by hanom on 27.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getGroupContactHelperBase().isThereAContact()){
      app.getGroupContactHelperBase().createContact(new ContactData("Виктор", "Викторович",
              null, null, null,
              null, null,
              null, null, "[none]"));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getGroupContactHelperBase().getContactList();
    app.getGroupContactHelperBase().selectContact(before.size() - 1);
    app.getGroupContactHelperBase().deleteSelectedContacts();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getGroupContactHelperBase().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
