package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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
    app.getGroupContactHelperBase().fillGroupForm(new GroupData("test1", null, null));
    app.getGroupContactHelperBase().submitGroupModification();
    app.getGroupContactHelperBase().returnToGroupPage();
    List<GroupData> after = app.getGroupContactHelperBase().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
