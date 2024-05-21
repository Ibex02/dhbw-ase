package de.dhbw.ase.wgEinkaufsliste.application.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.dhbw.ase.wgEinkaufsliste.application.user.command.ChangeNameCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.application.user.command.CreateUserCommand;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private GroupUserService groupService;

    @BeforeEach
    void setup() {
        encoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        groupService = mock(GroupUserService.class);
        userService = new UserService(encoder, userRepository, groupService);
    }

    @Test
    void findById_UserExists_ReturnsUser() {
        UserId userId = new UserId("1");
        User expectedUser = new User(new Email("email@example.com"), "encodedPassword", "John Doe");
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        Optional<User> result = userService.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get());
    }

    @Test
    void create_UserNotExists_ReturnsNewUser() throws UserAlreadyExistsException {
        CreateUserCommand command = new CreateUserCommand(new Email("email@example.com"), "password", "John Doe");
        when(userRepository.findByEmail(command.email())).thenReturn(Optional.empty());
        when(encoder.encode(command.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.create(command);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(new Email("email@example.com"), result.getEmail());
    }

    @Test
    void create_UserAlreadyExists_ThrowsException() {
        CreateUserCommand command = new CreateUserCommand(new Email("email@example.com"), "password", "John Doe");
        User existingUser = new User(new Email("email@example.com"), "encodedPassword", "John Doe");
        when(userRepository.findByEmail(command.email())).thenReturn(Optional.of(existingUser));

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.create(command);
        });
    }

    @Test
    void delete_CallsCorrectServices() {
        User user = new User(new Email("email@example.com"), "password", "John Doe");
        doNothing().when(groupService).removeUserFromAllGroups(user);
        doNothing().when(userRepository).deleteById(user.getId());

        userService.delete(user);

        verify(groupService).removeUserFromAllGroups(user);
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void changeName_ChangesNameAndSavesUser() {
        User user = new User(new Email("email@example.com"), "password", "John Doe");
        ChangeNameCommand command = new ChangeNameCommand(user, "Jane Doe");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = userService.changeName(command);

        assertEquals("Jane Doe", updatedUser.getName());
        verify(userRepository).save(user);
    }
}