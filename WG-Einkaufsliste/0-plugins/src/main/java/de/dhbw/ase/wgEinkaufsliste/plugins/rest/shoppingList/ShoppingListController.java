package de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListToShoppingListResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListManagementService;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList.request.ChangeShoppingListNameRequest;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.shoppingList.request.CreateShoppingListRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/lists")
public class ShoppingListController {

    private final ShoppingListManagementService shoppingListService;
    private final ShoppingListToShoppingListResourceMapper mapToResource;

    @Autowired
    public ShoppingListController(
            ShoppingListToShoppingListResourceMapper mapToResource,
            ShoppingListManagementService shoppingListService) {
        this.mapToResource = mapToResource;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", content = @Content)
    public ResponseEntity<List<ShoppingListResource>> getLists(@RequestParam String groupId) {
        try {
            var lists = shoppingListService.getAll(new GroupId(groupId));
            var resources = lists.stream().map(mapToResource).toList();

            return ResponseEntity.ok(resources);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404")
    public ResponseEntity<ShoppingListResource> createList(CreateShoppingListRequest request) {
        try {
            var shoppingList = shoppingListService.create(new GroupId(request.groupId()), request.name());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404")
    public ResponseEntity<ShoppingListResource> get(@PathVariable String id) {
        return shoppingListService.findById(new ShoppingListId(id))
                .map(mapToResource).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content)
    @ApiResponse(responseCode = "404", content = @Content)
    public HttpStatusCode delete(@PathVariable String id) {
        try {
            shoppingListService.delete(new ShoppingListId(id));

            return HttpStatus.OK;
        } catch (ShoppingListNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping("/{id}/name")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<ShoppingListResource> changeName(@PathVariable String id, @RequestBody ChangeShoppingListNameRequest request) {
        try {
            var shoppingList = shoppingListService.changeName(new ShoppingListId(id), request.newName());
            var resource = mapToResource.apply(shoppingList);

            return ResponseEntity.ok(resource);
        } catch (ShoppingListNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}