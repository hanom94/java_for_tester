package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by hanom on 26.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    if (! app.getGroupContactHelperBase().isThereAContact()){
      app.getGroupContactHelperBase().createContact(new ContactData("Виктор", "Викторович",
              null, null, null,
              null, null,
              null, null, "test1"));
    }
    int before = app.getGroupContactHelperBase().getContactCount();
    app.getNavigationHelper().gotoHomePage();
    app.getGroupContactHelperBase().initContactModification(before - 1);
    app.getGroupContactHelperBase().fillContactForm(new ContactData("Виктор", "Викторович",
            "ViktorXX", "Киев", "+380988888888",
            "+380999999999", "viktorxx@mail.ua",
            "Киев, Улица 1 ", "+380933333333", null), false);
    app.getGroupContactHelperBase().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getGroupContactHelperBase().getContactCount();
    Assert.assertEquals(after, before);

  }
}
