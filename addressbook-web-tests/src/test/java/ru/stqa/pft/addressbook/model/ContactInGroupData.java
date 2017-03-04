package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by hanom on 03.03.2017.
 */
@Entity
@Table(name = "address_in_groups")
public class ContactInGroupData {
    @Id
    @Column(name = "id")
    private int contactId = Integer.MAX_VALUE;
    @Column(name = "group_id")
    private int groupId = Integer.MIN_VALUE;

  public int getContactId() {
    return contactId;
  }

  public int getGroupId() {
    return groupId;
  }

  @Override
  public String toString() {
    return "ContactInGroupData{" +
            "contactId=" + contactId +
            ", groupId=" + groupId +
            '}';
  }
}
