package de.dhbw.ase.wgEinkaufsliste.application.group.command;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;

public record CreateGroupCommand(String name, User initialUser) { }