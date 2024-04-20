package de.dhbw.ase.wgEinkaufsliste.plugins.rest.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.resource.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupToGroupResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupService;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupUserService;
import de.dhbw.ase.wgEinkaufsliste.application.group.command.AddUserCommand;
import de.dhbw.ase.wgEinkaufsliste.application.group.command.RemoveUserCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.request.AddUserRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/groups/{groupId}/users")
public class GroupUserController {

    private final GroupService groupService;
    private final GroupToGroupResourceMapper mapToResource;

    @Autowired
    public GroupUserController(GroupService groupService, GroupToGroupResourceMapper mapToResource) {
        this.groupService = groupService;
        this.mapToResource = mapToResource;
    }

    @PutMapping("")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<GroupResource> addUser(@PathVariable String groupId, AddUserRequest request) {
        try {
            var command = new AddUserCommand(new GroupId(groupId), new UserId(request.userId()));
            var group = groupService.addUser(command);
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{userId}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<GroupResource> removeUser(@PathVariable String groupId, @PathVariable String userId) {
        try {
            var command = new RemoveUserCommand(new GroupId(groupId), new UserId(userId));
            var group = groupService.removeUser(command);
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}