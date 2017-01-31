package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getGroupContactHelperBase().initContactCreation();
    app.getGroupContactHelperBase().fillContactForm(new ContactData("Виктор", "Викторович",
            null, null, null,
            null, null,
            null, null, "test1"), true);
    app.getGroupContactHelperBase().submitContactCreation();
  }

}