package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListItemResourceToShoppingListItemMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/lists")
public class ShoppingListController {

    private final GroupRepository groupRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListToShoppingListResourceMapper mapToResource;
    private final ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource;
    private final ShoppingListApplicationService shoppingListService;

    @Autowired
    public ShoppingListController(
            GroupRepository groupRepository, ShoppingListRepository shoppingListRepository, ShoppingListToShoppingListResourceMapper mapToResource,
            ShoppingListItemResourceToShoppingListItemMapper mapItemFromResource,
            ShoppingListApplicationService shoppingListService) {
        this.groupRepository = groupRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.mapToResource = mapToResource;
        this.mapItemFromResource = mapItemFromResource;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("")
    @ResponseBody
    public List<ShoppingListResource> getLists(Authentication auth, @RequestParam String groupId) {
        return null;
    }

    @PostMapping("")
    public String createList(Authentication auth, CreateShoppingListRequest request) {
        var group = groupRepository.findById(request.groupId());
        var list = shoppingListService.create(group, request.name());
        return list.getId();
    }

    @GetMapping("/{listId}")
    public ShoppingListResource get(Authentication auth, @PathVariable String listId) {
        var list = shoppingListRepository.findById(listId);
        return mapToResource.apply(list);
    }

    @DeleteMapping("/{listId}")
    public void delete(Authentication auth, @PathVariable String listId) {
        var list = shoppingListRepository.findById(listId);
        shoppingListService.delete(list);
    }

    @PostMapping("/{listId}/items")
    public void addItem(Authentication auth, @PathVariable String listId, @RequestBody ShoppingListItemResource item) {
        var listItem = mapItemFromResource.apply(item);
        var list = shoppingListRepository.findById(listId);

        shoppingListService.addItem(list, listItem);
    }

//    @PutMapping("/{listId}/items/{itemId}")
//    public void modifyItem(@PathVariable String listId, @PathVariable String itemId) {
//
//    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public void deleteItem(Authentication auth, @PathVariable String listId, @PathVariable String itemId) {
        var list = shoppingListRepository.findById(listId);

        shoppingListService.deleteItemById(list, itemId);

    }

    @PutMapping("/{listId}/name")
    public void changeName(Authentication auth, @PathVariable String listId, @RequestBody ChangeShoppingListNameRequest request) {
        var list = shoppingListRepository.findById(listId);
        shoppingListService.changeName(list, request.newName());
    }

}
