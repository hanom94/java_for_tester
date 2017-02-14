package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupContact().allGroup().size() == 0){
            app.groupContact().createGroup(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.groupContact().allGroup();
        GroupData deletedGroup = before.iterator().next();
        app.groupContact().deleteGroup(deletedGroup);
        Set<GroupData> after = app.groupContact().allGroup();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }
}
