package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResourceToShoppingListItemMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList.request.ChangeShoppingListNameRequest;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList.request.CreateShoppingListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ShoppingListToShoppingListResourceMapper mapToResource;
    private final ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource;

    @Autowired
    public ShoppingListController(
            ShoppingListToShoppingListResourceMapper mapToResource,
            ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource,
            ShoppingListService shoppingListService) {
        this.mapToResource = mapToResource;
        this.mapItemFromResource = mapItemFromResource;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("")
    public List<ShoppingListResource> getLists(@RequestParam String groupId) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<ShoppingListResource> createList(CreateShoppingListRequest request) {
        try {
            var shoppingList = shoppingListService.createById(new GroupId(request.groupId()), request.name());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResource> get(@PathVariable String id) {
        return shoppingListService.getById(new ShoppingListId(id))
                .map(mapToResource).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        try {
            shoppingListService.deleteById(new ShoppingListId(id));
        } catch (ShoppingListNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ShoppingListResource> addItem(@PathVariable String id, @RequestBody ShoppingListItemResource item) {
        try {
            var listItem = mapItemFromResource.apply(item);
            var shoppingList = shoppingListService.addItemById(new ShoppingListId(id), listItem);
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{listId}/items/{itemId}")
//    public void modifyItem(@PathVariable String listId, @PathVariable String itemId) {
//
//    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<ShoppingListResource> deleteItem(@PathVariable String id, @PathVariable String itemId) {
        try {
            var shoppingList = shoppingListService.deleteItemById(new ShoppingListId(id), itemId);
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<ShoppingListResource> changeName(@PathVariable String id, @RequestBody ChangeShoppingListNameRequest request) {
        try {
            var shoppingList = shoppingListService.changeNameById(new ShoppingListId(id), request.newName());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
