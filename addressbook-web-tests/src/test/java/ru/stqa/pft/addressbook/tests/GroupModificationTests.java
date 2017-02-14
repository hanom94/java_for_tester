package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by hanom on 26.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.groupContact().groupList().size() == 0){
      app.groupContact().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testModificationGroup() {
    List<GroupData> before = app.groupContact().groupList();
    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getId(),"test1", "test2", "test3");
    app.groupContact().modifyGroup(index, group);
    List<GroupData> after = app.groupContact().groupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
