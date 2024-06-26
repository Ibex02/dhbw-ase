package de.dhbw.ase.wgEinkaufsliste.plugins.rest.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.resource.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupToGroupResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.request.ChangeGroupNameRequest;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.request.CreateGroupRequest;
import de.dhbw.ase.wgEinkaufsliste.application.group.command.ChangeNameCommand;
import de.dhbw.ase.wgEinkaufsliste.application.group.command.CreateGroupCommand;
import de.dhbw.ase.wgEinkaufsliste.application.user.CurrentUserProvider;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/groups")
public class GroupController {

    private final GroupService groupService;
    private final CurrentUserProvider context;
    private final GroupToGroupResourceMapper mapToResource;

    @Autowired
    public GroupController(GroupService groupService, CurrentUserProvider context, GroupToGroupResourceMapper mapToResource) {
        this.groupService = groupService;
        this.context = context;
        this.mapToResource = mapToResource;
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<GroupResource> getGroup(@PathVariable String id) {
        return groupService.findById(new GroupId(id))
                .map(mapToResource).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<GroupResource>> getAllGroups() {
        var user = context.getUser();
        var groups = groupService.getAllForUser(user).stream().map(mapToResource).toList();

        return ResponseEntity.ok(groups);
    }

    @PostMapping("")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<GroupResource> createGroup(@RequestBody CreateGroupRequest request) {
        var user = context.getUser();
        var command = new CreateGroupCommand(request.name(), user);
        var group = groupService.create(command);
        var resource = mapToResource.apply(group);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content)
    @ApiResponse(responseCode = "404", content = @Content)
    public HttpStatusCode deleteGroup(@PathVariable String id) {
        try {
            groupService.delete(new GroupId(id));
            return HttpStatus.OK;
        } catch (GroupNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping("/{id}/name")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public ResponseEntity<GroupResource> changeName(@PathVariable String id, ChangeGroupNameRequest request) {
        try {
            var command = new ChangeNameCommand(new GroupId(id), request.newName());
            var group = groupService.changeName(command);
            var resource = mapToResource.apply(group);

            return ResponseEntity.ok(resource);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}