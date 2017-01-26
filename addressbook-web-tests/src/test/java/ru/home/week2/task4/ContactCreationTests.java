package ru.home.week2.task4;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.TestBase;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("Виктор", "Викторович",
            "ViktorXX", "Киев", "+380988888888",
            "+380999999999", "viktorxx@mail.ua",
            "Киев, Улица 1 ", "+380933333333"));
    submitContactCreation();
  }

}