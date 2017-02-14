package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    Set<ContactData> before = app.groupContact().allContact();
    ContactData contact = new ContactData().withLastname("Иванов").withFirstname("Виктор")
            .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
            .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]");
    app.groupContact().createContact(contact);
    Set<ContactData> after = app.groupContact().allContact();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}