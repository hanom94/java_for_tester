package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.groupContact().allGroup();
        GroupData deletedGroup = before.iterator().next();
        app.groupContact().deleteGroup(deletedGroup);
        assertThat(app.groupContact().GroupCount(), equalTo(before.size() - 1));
        Groups after = app.groupContact().allGroup();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
