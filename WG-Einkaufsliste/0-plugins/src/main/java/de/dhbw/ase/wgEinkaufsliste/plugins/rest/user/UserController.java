package de.dhbw.ase.wgEinkaufsliste.plugins.rest.user;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.UserResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.UserToUserResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserAlreadyExistsException;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserService;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/create-new")
    @SecurityRequirements()
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", content = @Content)
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest request) {
        try {
            var user = userService.create(request.email(), request.password(), request.name());
            var resource = toResourceMapper.apply(user);

            return ResponseEntity.ok(resource);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<UserResource> get() {
        var user = context.getUser();
        var resource = toResourceMapper.apply(user);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("")
    @ApiResponse(responseCode = "200", content = @Content)
    public HttpStatusCode delete() {
        var user = context.getUser();
        userService.delete(user);

        return HttpStatus.OK;
    }

    @PutMapping("/name")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<UserResource> changeName(@RequestBody ChangeUserNameRequest request) {
        var user = context.getUser();

        var updatedUser = userService.changeName(user, request.newName());
        var resource = toResourceMapper.apply(updatedUser);

        return ResponseEntity.ok(resource);
    }
}
