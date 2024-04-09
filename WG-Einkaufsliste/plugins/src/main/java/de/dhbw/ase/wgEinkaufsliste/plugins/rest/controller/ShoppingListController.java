package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/lists")
public class ShoppingListController {

    @GetMapping("/{listId}")
    public ShoppingListResource get(@PathVariable String listId) {
        return null;
    }

    @DeleteMapping("/{listId}")
    public void delete(@PathVariable String listId) {

    }

    @PostMapping("/{listId}/items")
    public void addItem(@PathVariable String listId) {

    }

    @PutMapping("/{listId}/items/{itemId}")
    public void modifyItem(@PathVariable String listId, @PathVariable String itemId) {

    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public void deleteItem(@PathVariable String listId, @PathVariable String itemId) {

    }

    @PutMapping("/{listId}/name")
    public void changeName(Authentication auth, @PathVariable String listId, String newName) {

    }

}
