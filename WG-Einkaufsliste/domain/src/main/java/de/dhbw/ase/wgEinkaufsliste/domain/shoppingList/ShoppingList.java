package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import jakarta.persistence.Id;
import org.apache.commons.lang3.Validate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document("shopping-lists")
public class ShoppingList {
    @Id
    private UUID id;
    private String name;
    private List<ShoppingListItem> list = new ArrayList<>();
    private UUID groupId;

    protected ShoppingList() { }

    public ShoppingList(String name) {
        Validate.notBlank(name);

        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addItem(ShoppingListItem item) {
        list.add(item);
    }
}
