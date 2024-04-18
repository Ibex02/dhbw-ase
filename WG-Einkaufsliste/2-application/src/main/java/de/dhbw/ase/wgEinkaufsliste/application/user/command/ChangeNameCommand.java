package de.dhbw.ase.wgEinkaufsliste.application.user.command;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;

public record ChangeNameCommand(User user, String newName) { }