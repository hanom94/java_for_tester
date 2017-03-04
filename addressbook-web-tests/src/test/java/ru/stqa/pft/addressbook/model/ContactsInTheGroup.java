package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hanom on 03.03.2017.
 */
public class ContactsInTheGroup extends ForwardingSet<ContactInGroupData> {
    private Set<ContactInGroupData> deligate;

    public ContactsInTheGroup(Collection<ContactInGroupData> contacts){
      this.deligate = new HashSet<ContactInGroupData>(contacts);
    }
    @Override
    protected Set<ContactInGroupData> delegate() {
      return this.deligate;
    }
}
