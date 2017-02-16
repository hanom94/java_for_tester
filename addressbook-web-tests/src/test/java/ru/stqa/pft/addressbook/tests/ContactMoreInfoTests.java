package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hanom on 16.02.2017.
 */
public class ContactMoreInfoTests extends TestBase{

  @Test
  public void testContactMoreInfo() {
    app.goTo().homePage();
    if (app.groupContact().allContact().size() == 0) {
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333")
              .withEmail("viktorxx@mail.ua").withAddress2(null).withGroup("[none]"));
    }
    app.goTo().homePage();
    ContactData contact = app.groupContact().allContact().iterator().next();
    ContactData contactInfoFromEditForm = app.groupContact().infoFIOFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.groupContact().infoFromDetailsForm(contact);

    assertThat(contactInfoFromDetailsForm.getAllData(), equalTo(mergeData(contactInfoFromEditForm)));
  }

  private String mergeData(ContactData contact) {
    return Arrays.asList(contact.getFIO(), contact.getAddress(), contact.getHomeTelephone(),
            contact.getMobileTelephone(), contact.getPhone2(), contact.getEmail())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
