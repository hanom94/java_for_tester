package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String homeTelephone;
  private final String mobileTelephone;
  private final String email;
  private final String address2;
  private final String phone2;

  public ContactData(String firstname, String lastname, String nickname, String address, String homeTelephone, String mobileTelephone, String email, String address2, String phone2) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.homeTelephone = homeTelephone;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.address2 = address2;
    this.phone2 = phone2;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomeTelephone() {
    return homeTelephone;
  }

  public String getMobileTelephone() {
    return mobileTelephone;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
  }
}
