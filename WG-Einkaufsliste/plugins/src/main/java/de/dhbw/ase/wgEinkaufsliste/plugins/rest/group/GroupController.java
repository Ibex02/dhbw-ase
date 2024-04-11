package de.dhbw.ase.wgEinkaufsliste.plugins.rest.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.GroupToGroupResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.ShoppingListResource;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupApplicationService;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.plugins.authentication.UserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${apiPrefix}/groups")
public class GroupController {
    private final GroupApplicationService groupService;
    private final ShoppingListApplicationService shoppingListService;
    private final UserResolver userResolver;

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupToGroupResourceMapper mapToResource;

    @Autowired
    public GroupController(GroupApplicationService groupService, ShoppingListApplicationService shoppingListService, UserResolver userResolver, GroupRepository groupRepository, UserRepository userRepository, GroupToGroupResourceMapper mapToResource) {
        this.groupService = groupService;
        this.shoppingListService = shoppingListService;
        this.userResolver = userResolver;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.mapToResource = mapToResource;
    }

    @GetMapping("/{groupId}")
    public GroupResource getGroup(Authentication auth, @PathVariable String groupId) {

        var group = groupRepository.findById(groupId);
        return mapToResource.apply(group);
    }

    @GetMapping("")
    public List<GroupResource> getAllGroups(Authentication auth) {
        User user = userResolver.getUser(auth);

        var result = new ArrayList<GroupResource>();

        for (var groupId : user.getGroupIds()) {
            var group = groupRepository.findById(groupId);

            result.add(mapToResource.apply(group));
        }

        return result;
    }

    @PostMapping("")
    public String createGroup(Authentication auth, @RequestBody CreateGroupRequest request) {

        User user = userResolver.getUser(auth);

        Group group = groupService.create(user, request.name());
        return group.getId();
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(Authentication auth, @PathVariable String groupId) {
        var group = groupRepository.findById(groupId);
        groupService.delete(group);
    }

    @PutMapping("/{groupId}/name")
    public void changeName(Authentication auth, @PathVariable String groupId, ChangeGroupNameRequest request) {
        var group = groupRepository.findById(groupId);
        groupService.changeName(group, request.newName());
    }

    @PutMapping("/{groupId}/users")
    public void addUser(Authentication auth, @PathVariable String groupId, AddUserRequest request) {
        var group = groupRepository.findById(groupId);
        var user = userRepository.findById(request.userId());
        groupService.addUser(group, user);
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public void removeUser(Authentication auth, @PathVariable String groupId, @PathVariable String userId) {
        var group = groupRepository.findById(groupId);
        var user = userRepository.findById(userId);
        groupService.removeUser(group, user);
    }
}
