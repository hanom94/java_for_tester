package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupContactHelperBase().isThereAGroup()){
            app.getGroupContactHelperBase().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupContactHelperBase().selectGroup();
        app.getGroupContactHelperBase().deleteSelectedGroups();
        app.getGroupContactHelperBase().returnToGroupPage();
    }

}
