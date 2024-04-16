package de.dhbw.ase.wgEinkaufsliste.application.user;

public class UserNotFoundException extends Exception {

    private final String userId;

    public UserNotFoundException(String groupId) {
        super("No group for id: " + groupId + " found");

        this.userId = groupId;
    }

    public String getUserId() {
        return userId;
    }
}
