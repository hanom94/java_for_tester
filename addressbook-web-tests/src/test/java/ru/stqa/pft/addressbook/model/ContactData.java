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
  private String group;

  public ContactData(String firstname, String lastname, String nickname, String address, String homeTelephone,
                     String mobileTelephone, String email, String address2, String phone2, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.homeTelephone = homeTelephone;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.address2 = address2;
    this.phone2 = phone2;
    this.group = group;
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

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", address='" + address + '\'' +
            ", homeTelephone='" + homeTelephone + '\'' +
            ", mobileTelephone='" + mobileTelephone + '\'' +
            ", email='" + email + '\'' +
            ", address2='" + address2 + '\'' +
            ", phone2='" + phone2 + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homeTelephone != null ? !homeTelephone.equals(that.homeTelephone) : that.homeTelephone != null) return false;
    if (mobileTelephone != null ? !mobileTelephone.equals(that.mobileTelephone) : that.mobileTelephone != null)
      return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
    if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
    return group != null ? group.equals(that.group) : that.group == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homeTelephone != null ? homeTelephone.hashCode() : 0);
    result = 31 * result + (mobileTelephone != null ? mobileTelephone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (address2 != null ? address2.hashCode() : 0);
    result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    return result;
  }
}
