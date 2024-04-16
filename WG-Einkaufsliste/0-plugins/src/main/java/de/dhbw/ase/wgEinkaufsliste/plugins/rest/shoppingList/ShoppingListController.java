package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResourceToShoppingListItemMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListService;
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
            var shoppingList = shoppingListService.createById(request.groupId(), request.name());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{shoppingListId}")
    public ResponseEntity<ShoppingListResource> get(@PathVariable String shoppingListId) {
        return shoppingListService.getById(shoppingListId)
                .map(mapToResource).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{shoppingListId}")
    public void delete(@PathVariable String shoppingListId) {
        try {
            shoppingListService.deleteById(shoppingListId);
        } catch (ShoppingListNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{shoppingListId}/items")
    public ResponseEntity<ShoppingListResource> addItem(@PathVariable String shoppingListId, @RequestBody ShoppingListItemResource item) {
        try {
            var listItem = mapItemFromResource.apply(item);
            var shoppingList = shoppingListService.addItemById(shoppingListId, listItem);
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

    @DeleteMapping("/{shoppingListId}/items/{itemId}")
    public ResponseEntity<ShoppingListResource> deleteItem(@PathVariable String shoppingListId, @PathVariable String itemId) {
        try {
            var shoppingList = shoppingListService.deleteItemById(shoppingListId, itemId);
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{shoppingListId}/name")
    public ResponseEntity<ShoppingListResource> changeName(@PathVariable String shoppingListId, @RequestBody ChangeShoppingListNameRequest request) {
        try {
            var shoppingList = shoppingListService.changeNameById(shoppingListId, request.newName());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
