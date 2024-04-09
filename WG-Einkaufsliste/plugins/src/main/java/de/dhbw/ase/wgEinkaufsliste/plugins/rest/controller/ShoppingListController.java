package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.ShoppingListDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1.0/lists")
public class ShoppingListController {

    @GetMapping("/{listId}")
    public ShoppingListDTO get(@PathVariable String listId) {
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

}
