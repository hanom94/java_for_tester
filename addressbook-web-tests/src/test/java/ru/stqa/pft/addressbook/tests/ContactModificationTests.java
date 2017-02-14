package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by hanom on 26.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.groupContact().groupList().size() == 0){
      app.groupContact().createGroup(new GroupData("test1", null, null));
    }
    app.goTo().homePage();
    if (app.groupContact().contactList().size() == 0){
      app.groupContact().createContact(new ContactData("Виктор", "Иванов",
              null, null, null,
              null, null,
              null, null, "[none]"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification (){
    List<ContactData> before = app.groupContact().contactList();
    int index = before.size() -1;
    ContactData contact = new ContactData(before.get(index).getId(),"Виктор", "Иванов",
            "ViktorXX", "Киев", "+380988888888",
            "+380999999999", "viktorxx@mail.ua",
            "Киев, Улица 1 ", "+380933333333", null);
    app.groupContact().modifyContact(index, contact);
    List<ContactData> after = app.groupContact().contactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
