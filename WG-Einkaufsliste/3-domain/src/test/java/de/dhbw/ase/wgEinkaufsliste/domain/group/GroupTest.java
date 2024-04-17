package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    @Test
    public void testConstructorWithDefaults() {
        Group group = new Group("Family Group");
        assertNotNull(group.getId());
        assertEquals("Family Group", group.getName());
        assertTrue(group.getUsersIds().isEmpty());
        assertTrue(group.getListIds().isEmpty());
    }

    @Test
    public void testFullConstructorInitialization() {
        GroupId groupId = new GroupId();
        List<UserId> userIds = Arrays.asList(new UserId());
        List<ShoppingListId> listIds = Arrays.asList(new ShoppingListId());

        Group group = new Group(groupId, "Work Group", userIds, listIds);
        assertEquals(groupId, group.getId());
        assertEquals("Work Group", group.getName());
        assertEquals(1, group.getUsersIds().size());
        assertEquals(1, group.getListIds().size());
    }

    @Test
    public void testSetName() {
        Group group = new Group("Initial Name");
        group.setName("Updated Name");
        assertEquals("Updated Name", group.getName());
    }

    @Test
    public void testSetNameBlank() {
        Group group = new Group("Valid Name");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            group.setName("");
        });
    }

    @Test
    public void testAddUser() {
        Group group = new Group("Some Group");
        User user = new User("user@example.com", "password123", "User Name");
        group.addUser(user);

        assertTrue(group.getUsersIds().contains(user.getId()));
        assertTrue(user.getGroupIds().contains(group.getId()));
    }

    @Test
    public void testRemoveUser() {
        Group group = new Group("Some Group");
        User user = new User("user@example.com", "password123", "User Name");
        group.addUser(user);
        boolean result = group.removeUser(user);

        assertTrue(result);
        assertFalse(group.getUsersIds().contains(user.getId()));
        assertFalse(user.getGroupIds().contains(group.getId()));
    }

    @Test
    public void testAddList() {
        Group group = new Group("Some Group");
        ShoppingList shoppingList = new ShoppingList(new Group(group.getName()), "List Name");
        group.addList(shoppingList);

        assertTrue(group.getListIds().contains(shoppingList.getId()));
    }

    @Test
    public void testRemoveList() {
        Group group = new Group("Some Group");
        ShoppingList shoppingList = new ShoppingList(new Group(group.getName()), "List Name");
        group.addList(shoppingList);
        boolean result = group.removeList(shoppingList);

        assertTrue(result);
        assertFalse(group.getListIds().contains(shoppingList.getId()));
    }

    @Test
    public void testGroupIsEmpty() {
        Group group = new Group("Empty Group");
        assertTrue(group.isEmpty());

        User user = new User("user@example.com", "password123", "User Name");
        group.addUser(user);
        assertFalse(group.isEmpty());

        group.removeUser(user);
        assertTrue(group.isEmpty());
    }
}
