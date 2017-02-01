package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

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
    app.getGroupContactHelperBase().selectContact();
    app.getGroupContactHelperBase().deleteSelectedContacts();
  }
}
