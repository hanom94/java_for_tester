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
public class ContactEmailTests extends TestBase {

  @Test
  public void testContactEmails(){
    app.goTo().homePage();
    ContactData email = app.groupContact().allContact().iterator().next();
    ContactData emailInfoFromEditForm = app.groupContact().infoFromEditFrom(email);

    assertThat(email.getEmail(), equalTo(mergeMails(emailInfoFromEditForm)));
  }

  private String mergeMails(ContactData email) {
    return Arrays.asList(email.getEmail())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String email) {
    return email.replaceAll("[$#&^№*()]", "");
  }
}
