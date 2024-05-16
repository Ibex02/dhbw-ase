package de.dhbw.ase.wgEinkaufsliste.adapters.representations.user;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.request.CreateUserRequest;
import de.dhbw.ase.wgEinkaufsliste.application.user.command.CreateUserCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateRequestToCreateCommandMapper implements Function<CreateUserRequest, CreateUserCommand> {

    @Override
    public CreateUserCommand apply(CreateUserRequest request) {
        return map(request);
    }

    private CreateUserCommand map(CreateUserRequest request) {
        var email = new Email(request.email());
        return new CreateUserCommand(email, request.password(), request.name());
    }
}
