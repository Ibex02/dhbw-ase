package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.groups.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingLists.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.application.groups.GroupsApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/groups")
public class GroupsController {
    private final GroupsApplicationService groupsService;

    @Autowired
    public GroupsController(GroupsApplicationService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping("/{groupId}")
    public GroupResource getGroup(Authentication auth, @PathVariable String groupId) {
        return null;
    }

    @GetMapping("")
    public List<GroupResource> getAllGroups(Authentication auth) {
        return null;
    }

    @PostMapping("")
    public String createGroup(Authentication auth, String name) {

        Group group = groupsService.createNewGroup();
        return "created: " + group.getId() + " " + auth.getName();
    }

    @PutMapping("/{groupId}/name")
    public void changeName(Authentication auth, @PathVariable String groupId, String newName) {

    }

    @PutMapping("/{groupId}/users")
    public void addUser(Authentication auth, @PathVariable String groupId, String userId) {

    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public void removeUser(Authentication auth, @PathVariable String groupId, @PathVariable String userId) {

    }

    @GetMapping("/{groupId}/lists")
    public List<ShoppingListResource> getLists(Authentication auth, @PathVariable String groupId) {
        return null;
    }

    @PostMapping("/{groupId}/lists")
    public void createList(Authentication auth, @PathVariable String groupId) {

    }
}
