package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by hanom on 26.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    if (app.groupContact().allContact().size() == 0){
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
              .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification (){
    Contacts before = app.groupContact().allContact();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Иванов")
            .withFirstname("Виктор").withNickname("ViktorXX").withAddress("Киев").withHomeTelephone("+380988888888")
            .withMobileTelephone("+380999999999").withEmail("viktorxx@mail.ua")
            .withAddress2("Киев, Улица 1 ").withPhone2("+380933333333").withGroup(null);
    app.groupContact().modifyContact(contact);
    assertThat(app.groupContact().ContactCount(), equalTo(before.size()));
    Contacts after = app.groupContact().allContact();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
