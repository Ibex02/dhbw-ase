package de.dhbw.ase.wgEinkaufsliste.plugins.rest.controller;

import de.dhbw.ase.wgEinkaufsliste.application.user.UserApplicationService;
import de.dhbw.ase.wgEinkaufsliste.plugins.authentication.UserResolver;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${apiPrefix}/users")
public class UserController {

    private final UserApplicationService userService;
    private final UserResolver userResolver;

    public UserController(UserApplicationService userService, UserResolver userResolver) {
        this.userService = userService;
        this.userResolver = userResolver;
    }

    @DeleteMapping("")
    public void delete(Authentication auth) {
        var user = userResolver.getUser(auth);

        userService.delete(user);
    }
}
