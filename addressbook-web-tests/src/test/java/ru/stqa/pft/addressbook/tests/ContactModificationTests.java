package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
      app.getGroupContactHelperBase().createContact(new ContactData("Виктор", "Иванов",
              null, null, null,
              null, null,
              null, null, "test1"));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getGroupContactHelperBase().getContactList();
    app.getGroupContactHelperBase().initContactModification(before.size() -1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Виктор", "Иванов",
            "ViktorXX", "Киев", "+380988888888",
            "+380999999999", "viktorxx@mail.ua",
            "Киев, Улица 1 ", "+380933333333", null);
    app.getGroupContactHelperBase().fillContactForm(contact, false);
    app.getGroupContactHelperBase().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getGroupContactHelperBase().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
