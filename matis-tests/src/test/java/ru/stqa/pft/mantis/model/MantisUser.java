package ru.stqa.pft.mantis.model;

/**
 * Created by hanom on 09.03.2017.
 */
public class MantisUser {
  private String username;
  private String password;
  private String email;

  public String getUsername() {
    return username;
  }

  public MantisUser withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public MantisUser withPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public MantisUser withEmail(String email) {
    this.email = email;
    return this;
  }

}
