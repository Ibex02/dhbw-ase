package de.dhbw.ase.wgEinkaufsliste.adapters.representations.user;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.request.CreateUserRequest;
import de.dhbw.ase.wgEinkaufsliste.application.user.command.CreateUserCommand;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateRequestToCreateCommandMapper implements Function<CreateUserRequest, CreateUserCommand> {

    @Override
    public CreateUserCommand apply(CreateUserRequest request) {
        return map(request);
    }

    private CreateUserCommand map(CreateUserRequest request) {
        return new CreateUserCommand(request.email(), request.password(), request.name());
    }
}
