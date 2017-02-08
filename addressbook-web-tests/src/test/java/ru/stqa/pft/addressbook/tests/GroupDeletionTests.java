package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupContactHelperBase().isThereAGroup()){
            app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = app.getGroupContactHelperBase().getGroupList();
        app.getGroupContactHelperBase().selectGroup(before.size() - 1);
        app.getGroupContactHelperBase().deleteSelectedGroups();
        app.getGroupContactHelperBase().returnToGroupPage();
        List<GroupData> after = app.getGroupContactHelperBase().getGroupList();
        Assert.assertEquals(after.size(), before.size() -1);
    }

}
