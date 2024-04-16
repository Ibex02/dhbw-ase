package de.dhbw.ase.wgEinkaufsliste.plugins.rest.user;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.UserResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.UserToUserResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/users")
public class UserController {

    private final UserService userService;
    private final UserContextProvider context;
    private final UserToUserResourceMapper toResourceMapper;

    public UserController(UserService userService, UserContextProvider context, UserToUserResourceMapper toResourceMapper) {
        this.userService = userService;
        this.context = context;
        this.toResourceMapper = toResourceMapper;
    }

    @GetMapping("")
    public ResponseEntity<UserResource> get() {
        var user = context.getUser();
        var resource = toResourceMapper.apply(user);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("")
    public void delete() {
        var user = context.getUser();
        userService.delete(user);
    }

    @PutMapping("/name")
    public ResponseEntity<UserResource> changeName(@RequestBody String newName) {
        var user = context.getUser();

        var updatedUser = userService.changeName(user, newName);
        var resource = toResourceMapper.apply(updatedUser);

        return ResponseEntity.ok(resource);
    }
}
