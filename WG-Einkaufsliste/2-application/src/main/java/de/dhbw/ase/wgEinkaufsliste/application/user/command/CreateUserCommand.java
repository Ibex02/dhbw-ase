package de.dhbw.ase.wgEinkaufsliste.application.user.command;

import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;

public record CreateUserCommand(Email email, String password, String name) {
}
