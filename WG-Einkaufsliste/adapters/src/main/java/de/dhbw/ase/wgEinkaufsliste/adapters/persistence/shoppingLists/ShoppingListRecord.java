package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.values.Price;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shopping-items")
public class ShoppingListRecord {

    @Id
    private final String id;
    private final String groupId;
    private final String name;
    private final List<ShoppingListRecordItem> items;

    public ShoppingListRecord(String id, String groupId, String name, List<ShoppingListRecordItem> items) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<ShoppingListRecordItem> getItems() {
        return items;
    }

    public record ShoppingListRecordItem(String id, String name, int amount, double price) { }
}
