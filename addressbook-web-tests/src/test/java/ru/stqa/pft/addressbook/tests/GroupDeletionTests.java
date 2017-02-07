package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupContactHelperBase().getGroupCount();
        if (! app.getGroupContactHelperBase().isThereAGroup()){
            app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupContactHelperBase().selectGroup(before - 1);
        app.getGroupContactHelperBase().deleteSelectedGroups();
        app.getGroupContactHelperBase().returnToGroupPage();
        int after = app.getGroupContactHelperBase().getGroupCount();
        Assert.assertEquals(after, before -1);
    }

}
