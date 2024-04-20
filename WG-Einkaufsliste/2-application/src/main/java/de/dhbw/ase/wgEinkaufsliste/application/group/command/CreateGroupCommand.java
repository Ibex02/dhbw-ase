package de.dhbw.ase.wgEinkaufsliste.application.group.command;

import org.apache.catalina.User;

public record CreateGroupCommand(String name, User initialUser) { }