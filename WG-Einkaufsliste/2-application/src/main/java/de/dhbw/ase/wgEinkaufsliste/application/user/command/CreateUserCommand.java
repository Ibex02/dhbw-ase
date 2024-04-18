package de.dhbw.ase.wgEinkaufsliste.application.user.command;

public record CreateUserCommand(String email, String password, String name) {
}
