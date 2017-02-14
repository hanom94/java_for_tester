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
  public void testContactModification (){
    List<ContactData> before = app.groupContact().contactList();
    int index = before.size() -1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Виктор")
            .withLastname("Иванов").withNickname("ViktorXX").withAddress("Киев").withHomeTelephone("+380988888888")
            .withMobileTelephone("+380999999999").withEmail("viktorxx@mail.ua")
            .withAddress2("Киев, Улица 1 ").withPhone2("+380933333333").withGroup(null);
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
