package de.dhbw.ase.wgEinkaufsliste.domain.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;

public class UserNotFoundException extends Exception {

    private final UserId id;

    public UserNotFoundException(UserId id) {
        super("No user for value: " + id + " found");

        this.id = id;
    }

    public UserId getId() {
        return id;
    }
}
