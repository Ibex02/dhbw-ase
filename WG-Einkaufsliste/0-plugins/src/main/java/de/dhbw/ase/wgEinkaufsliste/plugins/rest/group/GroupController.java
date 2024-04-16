package de.dhbw.ase.wgEinkaufsliste.plugins.rest.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupToGroupResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupService;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.group.request.AddUserRequest;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.group.request.ChangeGroupNameRequest;
import de.dhbw.ase.wgEinkaufsliste.plugins.rest.group.request.CreateGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserContextProvider context;

    private final GroupToGroupResourceMapper mapToResource;

    @Autowired
    public GroupController(GroupService groupService, UserContextProvider context, GroupToGroupResourceMapper mapToResource) {
        this.groupService = groupService;
        this.context = context;
        this.mapToResource = mapToResource;
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResource> getGroup(@PathVariable String groupId) {
        return groupService.getById(groupId)
                .map(mapToResource).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<GroupResource>> getAllGroups() {

        User user = context.getUser();
        var groups = groupService.getAllForUser(user).stream().map(mapToResource).toList();

        return ResponseEntity.ok(groups);
    }

    @PostMapping("")
    public ResponseEntity<GroupResource> createGroup(@RequestBody CreateGroupRequest request) {

        var user = context.getUser();
        var group = groupService.create(user, request.name());
        var resource = mapToResource.apply(group);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable String groupId) {
        groupService.deleteById(groupId);
    }

    @PutMapping("/{groupId}/name")
    public ResponseEntity<GroupResource> changeName(@PathVariable String groupId, ChangeGroupNameRequest request) {
        try {
            var group = groupService.changeNameById(groupId, request.newName());
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{groupId}/users")
    public ResponseEntity<GroupResource> addUser(@PathVariable String groupId, AddUserRequest request) {
        try {
            var group = groupService.addUserById(groupId, request.userId());
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<GroupResource> removeUser(@PathVariable String groupId, @PathVariable String userId) {
        try {
            var group = groupService.addUserById(groupId, userId);
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
