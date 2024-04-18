package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.AddItemRequestToAddItemCommandMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.resource.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListItemService;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.DeleteShoppingListItemCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.request.AddShoppingListItemRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/lists/{listId}/items")
public class ShoppingListItemController {

    private final ShoppingListItemService shoppingListService;
    private final ShoppingListToShoppingListResourceMapper mapToResource;
    private final AddItemRequestToAddItemCommandMapper mapRequestToCommand;

    @Autowired
    public ShoppingListItemController(
            ShoppingListToShoppingListResourceMapper mapToResource,
            ShoppingListItemService shoppingListService, AddItemRequestToAddItemCommandMapper mapRequestToCommand) {
        this.mapToResource = mapToResource;
        this.shoppingListService = shoppingListService;
        this.mapRequestToCommand = mapRequestToCommand;
    }

    @PostMapping("")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<ShoppingListResource> addOrUpdateItem(@PathVariable String listId, @RequestBody AddShoppingListItemRequest request) {
        try {
            var command = mapRequestToCommand.apply(Pair.of(new ShoppingListId(listId), request));
            var shoppingList = shoppingListService.addItem(command);
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
            var command = new DeleteShoppingListItemCommand(new ShoppingListId(listId), new ShoppingListItemId(itemId));
            var shoppingList = shoppingListService.deleteItem(command);
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}