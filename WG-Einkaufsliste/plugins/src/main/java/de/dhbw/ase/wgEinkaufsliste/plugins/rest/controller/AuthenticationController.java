package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.application.user.UserApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${apiPrefix}/authentication")
public class AuthenticationController {

    private final UserApplicationService userService;

    @Autowired
    public AuthenticationController(UserApplicationService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    @SecurityRequirements()
    public String test() {
        User user = userService.create("test", "test");
        return "created: " + user.getId();
    }
}
