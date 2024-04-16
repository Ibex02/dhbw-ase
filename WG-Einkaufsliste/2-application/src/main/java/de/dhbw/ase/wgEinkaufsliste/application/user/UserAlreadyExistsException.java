package de.dhbw.ase.wgEinkaufsliste.application.user;

public class UserAlreadyExistsException extends Exception {
    private final String email;

    public UserAlreadyExistsException(String email) {
        super("User with email: " + email + " already exists!");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
