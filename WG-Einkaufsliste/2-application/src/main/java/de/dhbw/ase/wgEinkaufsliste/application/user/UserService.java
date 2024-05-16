package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.application.group.GroupUserService;
import de.dhbw.ase.wgEinkaufsliste.application.user.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final GroupUserService groupService;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository, GroupUserService groupService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(Email email) {
        return userRepository.findByEmail(email);
    }

    public User create(CreateUserCommand command) throws UserAlreadyExistsException {
        var userOpt = userRepository.findByEmail(command.email());
        if (userOpt.isPresent()) throw new UserAlreadyExistsException(command.email());

        String encoded = encoder.encode(command.password());
        var user = new User(command.email(), encoded, command.name());

        userRepository.save(user);
        return user;
    }

    public void delete(User user) {
        groupService.removeUserFromAllGroups(user);
        userRepository.deleteById(user.getId());
    }

    public User changeName(ChangeNameCommand command) {
        var user = command.user();
        user.setName(command.newName());
        return userRepository.save(user);
    }
}