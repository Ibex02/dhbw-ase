package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserService;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1.0/authentication")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        User user = userService.creatUser("test", "test");
        return "created: " + user.getId();
    }
}
