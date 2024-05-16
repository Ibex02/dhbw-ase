package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;

public class UserAlreadyExistsException extends Exception {
    private final Email email;

    public UserAlreadyExistsException(Email email) {
        super("User with value: " + email.value() + " already exists!");
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
