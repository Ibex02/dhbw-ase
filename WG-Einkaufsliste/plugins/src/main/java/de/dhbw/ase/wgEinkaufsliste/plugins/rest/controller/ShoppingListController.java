package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResourceToShoppingListItemMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/lists")
public class ShoppingListController {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListToShoppingListResourceMapper mapToResource;
    private final ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource;
    private final ShoppingListApplicationService shoppingListService;

    public ShoppingListController(
            ShoppingListRepository shoppingListRepository, ShoppingListToShoppingListResourceMapper mapToResource, ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource,
            ShoppingListApplicationService shoppingListService) {
        this.shoppingListRepository = shoppingListRepository;
        this.mapToResource = mapToResource;
        this.mapItemFromResource = mapItemFromResource;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/{listId}")
    public ShoppingListResource get(@PathVariable String listId) {
        var list = shoppingListRepository.findById(listId);
        return mapToResource.apply(list);
    }

    @DeleteMapping("/{listId}")
    public void delete(@PathVariable String listId) {
        var list = shoppingListRepository.findById(listId);
        shoppingListService.delete(list);
    }

    @PostMapping("/{listId}/items")
    public void addItem(@PathVariable String listId, ShoppingListItemResource item) {
        var kp = mapItemFromResource.apply(item);
        var list = shoppingListRepository.findById(listId);

        shoppingListService.addItem(list, kp);

    }

//    @PutMapping("/{listId}/items/{itemId}")
//    public void modifyItem(@PathVariable String listId, @PathVariable String itemId) {
//
//    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public void deleteItem(@PathVariable String listId, @PathVariable String itemId) {
        var list = shoppingListRepository.findById(listId);

        shoppingListService.deleteItemById(list, itemId);

    }

    @PutMapping("/{listId}/name")
    public void changeName(Authentication auth, @PathVariable String listId, String newName) {
        var list = shoppingListRepository.findById(listId);
        shoppingListService.changeName(list, newName);
    }

}
