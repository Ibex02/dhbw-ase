package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.application.groups.GroupsApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String test(Authentication auth) {
        Group group = groupsService.createNewGroup();
        return "created: " + group.getId() + auth.getName();
    }
}
