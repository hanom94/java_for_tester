package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    Contacts before = app.groupContact().allContact();
    ContactData contact = new ContactData().withLastname("Иванов").withFirstname("Виктор")
            .withNickname(null).withAddress(null).withHomeTelephone("+380988888888").withMobileTelephone("+380999999999")
            .withEmail(null).withAddress2(null).withPhone2("+380933333333").withGroup("[none]");
    app.groupContact().createContact(contact);
    assertThat(app.groupContact().ContactCount(), equalTo(before.size() + 1));
    Contacts after = app.groupContact().allContact();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    if (app.groupContact().allGroup().size() == 0){
      app.groupContact().createGroup(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
    app.goTo().homePage();
    Contacts before = app.groupContact().allContact();
    ContactData contact = new ContactData().withLastname("Иванов'").withFirstname("Виктор")
            .withNickname(null).withAddress(null).withHomeTelephone(null).withMobileTelephone(null)
            .withEmail(null).withAddress2(null).withPhone2(null).withGroup("[none]");
    app.groupContact().createContact(contact);
    assertThat(app.groupContact().ContactCount(), equalTo(before.size() + 1));
    Contacts after = app.groupContact().allContact();
    assertThat(after, equalTo(before));
  }

}