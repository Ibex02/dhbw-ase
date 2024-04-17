package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {

    @Test
    public void testFullConstructorInitialization() {
        ShoppingListId listId = new ShoppingListId();
        GroupId groupId = new GroupId();
        ShoppingListItem item = new ShoppingListItem(new ShoppingListItemId(), "Milk", new Quantity(2));
        List<ShoppingListItem> items = Arrays.asList(item);

        ShoppingList shoppingList = new ShoppingList(listId, groupId, "Weekly Groceries", items);

        assertEquals(listId, shoppingList.getId());
        assertEquals(groupId, shoppingList.getGroupId());
        assertEquals("Weekly Groceries", shoppingList.getName());
        assertTrue(shoppingList.getItems().contains(item));
    }

    @Test
    public void testSimpleConstructor() {
        Group group = new Group("Family");
        ShoppingList shoppingList = new ShoppingList(group, "Daily Needs");

        assertEquals(group.getId(), shoppingList.getGroupId());
        assertEquals("Daily Needs", shoppingList.getName());
        assertTrue(group.getListIds().contains(shoppingList.getId()));
    }

    @Test
    public void testNameValidation() {
        ShoppingListId listId = new ShoppingListId();
        GroupId groupId = new GroupId();
        List<ShoppingListItem> items = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> new ShoppingList(listId, groupId, "", items));
    }

    @Test
    public void testSetName() {
        var group = new Group("Family");
        var shoppingList = new ShoppingList(group, "Daily Needs");

        shoppingList.setName("Weekly Needs");

        assertEquals("Weekly Needs", shoppingList.getName());
    }

    @Test
    public void testAddOrUpdateItem() {
        ShoppingListId listId = new ShoppingListId();
        GroupId groupId = new GroupId();
        ShoppingListItem item = new ShoppingListItem(new ShoppingListItemId(), "Eggs", new Quantity(12));
        List<ShoppingListItem> items = new ArrayList<>();

        ShoppingList shoppingList = new ShoppingList(listId, groupId, "Food List", items);
        shoppingList.addOrUpdateItem(item);

        assertEquals(1, shoppingList.getItems().size());
        assertEquals(item, shoppingList.getItems().get(0));
    }

    @Test
    public void testRemoveItemById() {
        var listId = new ShoppingListId();
        var groupId = new GroupId();
        var item = new ShoppingListItem(new ShoppingListItemId(), "Butter", new Quantity(10));
        var items = Arrays.asList(item);

        var shoppingList = new ShoppingList(listId, groupId, "Food List", items);
        shoppingList.removeItemById(item.id());

        assertTrue(shoppingList.getItems().isEmpty());
    }
}