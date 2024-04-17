package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResourceToShoppingListItemMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListItemService;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListService;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/lists/{listId}/items")
public class ShoppingListItemController {

    private final ShoppingListItemService shoppingListService;
    private final ShoppingListToShoppingListResourceMapper mapToResource;
    private final ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource;

    @Autowired
    public ShoppingListItemController(
            ShoppingListToShoppingListResourceMapper mapToResource,
            ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource,
            ShoppingListItemService shoppingListService) {
        this.mapToResource = mapToResource;
        this.mapItemFromResource = mapItemFromResource;
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<ShoppingListResource> addItem(@PathVariable String listId, @RequestBody ShoppingListItemResource item) {
        try {
            var listItem = mapItemFromResource.apply(item);
            var shoppingList = shoppingListService.addOrUpdateItem(new ShoppingListId(listId), listItem);
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{itemId}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<ShoppingListResource> deleteItem(@PathVariable String listId, @PathVariable String itemId) {
        try {
            var shoppingList = shoppingListService.deleteItem(new ShoppingListId(listId), new ShoppingListItemId(itemId));
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}