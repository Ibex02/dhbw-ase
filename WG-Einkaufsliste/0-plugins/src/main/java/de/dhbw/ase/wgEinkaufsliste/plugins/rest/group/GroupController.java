package de.dhbw.ase.wgEinkaufsliste.plugins.rest.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupToGroupResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupService;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
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

    @GetMapping("/{id}")
    public ResponseEntity<GroupResource> getGroup(@PathVariable String id) {
        return groupService.getById(new GroupId(id))
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

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.deleteById(new GroupId(id));
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<GroupResource> changeName(@PathVariable String id, ChangeGroupNameRequest request) {
        try {
            var group = groupService.changeNameById(new GroupId(id), request.newName());
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/users")
    public ResponseEntity<GroupResource> addUser(@PathVariable String id, AddUserRequest request) {
        try {
            var group = groupService.addUserById(new GroupId(id), new UserId(request.userId()));
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/users/{userId}")
    public ResponseEntity<GroupResource> removeUser(@PathVariable String id, @PathVariable String userId) {
        try {
            var group = groupService.removeUserById(new GroupId(id), new UserId(userId));
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
