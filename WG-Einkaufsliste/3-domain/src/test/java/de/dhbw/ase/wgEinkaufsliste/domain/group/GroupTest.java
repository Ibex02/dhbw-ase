package de.dhbw.ase.wgEinkaufsliste.domain.group;

import static org.junit.jupiter.api.Assertions.*;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

public class GroupTest {

    private Group group;
    private UserId userId;
    private ShoppingListId listId;

    @BeforeEach
    void setUp() {
        group = new Group("Test Group");
        userId = new UserId(UUID.randomUUID().toString());
        listId = new ShoppingListId(UUID.randomUUID().toString());
    }

    @Test
    void constructor_SetsValuesCorrectly() {
        GroupId groupId = new GroupId();
        String groupName = "Family";
        HashSet<UserId> userIds = new HashSet<>(Collections.singletonList(userId));
        HashSet<ShoppingListId> listIds = new HashSet<>(Collections.singletonList(listId));

        Group testGroup = new Group(groupId, groupName, userIds, listIds);

        assertEquals(groupId, testGroup.getId());
        assertEquals(groupName, testGroup.getName());
        assertTrue(testGroup.getUsersIds().contains(userId));
        assertTrue(testGroup.getListIds().contains(listId));
    }

    @Test
    void setName_ValidName_NameIsSet() {
        String newName = "New Group Name";
        group.setName(newName);
        assertEquals(newName, group.getName());
    }

    @Test
    void setName_BlankName_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setName("");
        });
    }

    @Test
    void addUser_AddsUser() {
        group.addUser(userId);
        assertTrue(group.getUsersIds().contains(userId));
    }

    @Test
    void removeUser_RemovesUser() {
        group.addUser(userId);
        group.removeUser(userId);
        assertFalse(group.getUsersIds().contains(userId));
    }

    @Test
    void addList_AddsList() {
        group.addList(listId);
        assertTrue(group.getListIds().contains(listId));
    }

    @Test
    void removeList_RemovesList() {
        group.addList(listId);
        group.removeList(listId);
        assertFalse(group.getListIds().contains(listId));
    }

    @Test
    void isEmpty_ReturnsTrueIfNoUsers() {
        assertTrue(group.isEmpty());
    }

    @Test
    void isEmpty_ReturnsFalseIfUsersPresent() {
        group.addUser(userId);
        assertFalse(group.isEmpty());
    }
}