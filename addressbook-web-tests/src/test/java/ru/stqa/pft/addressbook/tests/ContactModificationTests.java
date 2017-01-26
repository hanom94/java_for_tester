package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by hanom on 26.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    app.getNavigationHelper().gotoHomePage();
    app.getGroupContactHelperBase().initContactModification();
    app.getGroupContactHelperBase().fillContactForm(new ContactData("Виктор", "Викторович",
            "ViktorXX", "Киев", "+380988888888",
            "+380999999999", "viktorxx@mail.ua",
            "Киев, Улица 1 ", "+380933333333"));
    app.getGroupContactHelperBase().submitContactModification();

  }
}
