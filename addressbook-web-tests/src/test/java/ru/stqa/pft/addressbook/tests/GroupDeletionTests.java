package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupContact().groupList().size() == 0){
            app.groupContact().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.groupContact().groupList();
        int index = before.size() -1;
        app.groupContact().deleteGroup(index);
        List<GroupData> after = app.groupContact().groupList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
