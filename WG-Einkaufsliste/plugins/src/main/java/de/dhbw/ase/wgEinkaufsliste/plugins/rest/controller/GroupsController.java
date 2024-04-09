package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.GroupDTO;
import de.dhbw.ase.wgEinkaufsliste.application.groups.GroupsApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1.0/groups")
public class GroupsController {
    private final GroupsApplicationService groupsService;

    @Autowired
    public GroupsController(GroupsApplicationService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping("/test")
    public String test(Authentication auth) {
        Group group = groupsService.createNewGroup();
        return "created: " + group.getId() + " " + auth.getName();
    }

    @GetMapping("/{id}")
    public GroupDTO getGroup(@PathVariable String id, Authentication auth) {
        return null;
    }

    @GetMapping("")
    public List<GroupDTO> getAllGroupsForUser(Authentication auth) {
        return null;
    }

    @PutMapping("/{id}/users")
    public void addUser() {

    }

    @DeleteMapping()
    public void removeUser() {

    }

    public void createList() {

    }



}
