package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hanom on 15.02.2017.
 */
public class ContactAddressTests extends TestBase {

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    if (app.groupContact().allContact().size() == 0){
      app.groupContact().createContact(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333")
              .withEmail("viktorxx@mail.ua").withAddress2(null));
    }
    app.goTo().homePage();
    ContactData address = app.groupContact().allContact().iterator().next();
    ContactData addressInfoFromEditForm = app.groupContact().infoFromEditFrom(address);

    assertThat(address.getAddress(), equalTo(mergeAddress(addressInfoFromEditForm)));
  }

  private String mergeAddress(ContactData address) {
    return Arrays.asList(address.getAddress())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactAddressTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String address) {
    return address.replaceAll("[$&^*]", "");
  }
}
