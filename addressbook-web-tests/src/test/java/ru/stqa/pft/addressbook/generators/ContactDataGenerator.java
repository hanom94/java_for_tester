package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanom on 21.02.2017.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "GroupContact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts,new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    System.out.println(new File(".").getAbsolutePath());
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getLastname(), contact.getFirstname()
            , contact.getNickname(), contact.getAddress(), contact.getHomeTelephone(), contact.getMobileTelephone()
            , contact.getPhone2(), contact.getEmail(), contact.getPhoto(), contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      File photo = new File("src/test/resources/stru.png");
      contacts.add(new ContactData().withLastname("Иванов").withFirstname("Виктор")
              .withNickname(null).withAddress("Киев, улица 1, дом 1").withHomeTelephone("+380988888888")
              .withMobileTelephone("+380999999999").withPhone2("+380933333333")
              .withEmail("viktorxx@mail.ua").withAddress2(null)
              .withPhoto(photo).withGroup("[none]"));
    }
    return contacts;
  }
}
