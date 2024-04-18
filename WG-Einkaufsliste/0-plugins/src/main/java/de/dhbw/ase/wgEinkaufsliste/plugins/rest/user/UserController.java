package de.dhbw.ase.wgEinkaufsliste.plugins.rest.user;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.CreateRequestToCreateCommandMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.resource.UserResource;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.UserToUserResourceMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.request.ChangeUserNameRequest;
import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.request.CreateUserRequest;
import de.dhbw.ase.wgEinkaufsliste.application.user.CurrentUserProvider;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserAlreadyExistsException;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${apiPrefix}/users")
public class UserController {

    private final UserService userService;
    private final CurrentUserProvider context;
    private final UserToUserResourceMapper toResourceMapper;
    private final CreateRequestToCreateCommandMapper createRequestMapper;

    public UserController(UserService userService, CurrentUserProvider context, UserToUserResourceMapper toResourceMapper, CreateRequestToCreateCommandMapper createRequestMapper) {
        this.userService = userService;
        this.context = context;
        this.toResourceMapper = toResourceMapper;
        this.createRequestMapper = createRequestMapper;
    }

    @PostMapping("/create-new")
    @SecurityRequirements()
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", content = @Content)
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest request) {
        try {
            var command = createRequestMapper.apply(request);
            var user = userService.create(command);
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