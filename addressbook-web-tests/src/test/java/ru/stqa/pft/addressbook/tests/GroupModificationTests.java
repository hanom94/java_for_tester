package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hanom on 26.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testModificationGroup() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupContactHelperBase().getGroupList();
    app.getGroupContactHelperBase().selectGroup(before.size() - 1);
    app.getGroupContactHelperBase().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
    app.getGroupContactHelperBase().fillGroupForm(group);
    app.getGroupContactHelperBase().submitGroupModification();
    app.getGroupContactHelperBase().returnToGroupPage();
    List<GroupData> after = app.getGroupContactHelperBase().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
