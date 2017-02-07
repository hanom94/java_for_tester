package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by hanom on 26.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testModificationGroup() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupContactHelperBase().getGroupCount();
    if (! app.getGroupContactHelperBase().isThereAGroup()){
      app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupContactHelperBase().selectGroup(before - 1);
    app.getGroupContactHelperBase().initGroupModification();
    app.getGroupContactHelperBase().fillGroupForm(new GroupData("test1", null, null));
    app.getGroupContactHelperBase().submitGroupModification();
    app.getGroupContactHelperBase().returnToGroupPage();
    int after = app.getGroupContactHelperBase().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
