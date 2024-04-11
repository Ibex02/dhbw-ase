package de.dhbw.ase.wgEinkaufsliste.plugins.rest.user;

import de.dhbw.ase.wgEinkaufsliste.application.user.UserApplicationService;
import de.dhbw.ase.wgEinkaufsliste.plugins.authentication.UserResolver;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/name")
    public void changeName(Authentication auth, @RequestBody String newName) {
        var user = userResolver.getUser(auth);

        userService.changeName(user, newName);
    }
}
