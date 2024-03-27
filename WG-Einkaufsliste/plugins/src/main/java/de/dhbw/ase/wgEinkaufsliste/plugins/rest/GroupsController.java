package de.dhbw.ase.wgEinkaufsliste.plugins.rest;

import de.dhbw.ase.wgEinkaufsliste.application.groups.GroupsApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1.0/groups")
public class GroupsController {
    private final GroupsApplicationService groupsService;

    @Autowired
    public GroupsController(GroupsApplicationService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping("/test")
    public String test() {
        Group group = groupsService.createNewGroup();
        return "created: " + group.getId();
    }
}
