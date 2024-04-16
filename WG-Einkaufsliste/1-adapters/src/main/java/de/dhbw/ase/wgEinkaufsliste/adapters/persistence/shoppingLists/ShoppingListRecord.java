package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shopping-items")
public record ShoppingListRecord(@Id String id, String groupId, String name, List<ShoppingListRecordItem> items) {
    public record ShoppingListRecordItem(String id, String name, int amount, double price) { }
}
