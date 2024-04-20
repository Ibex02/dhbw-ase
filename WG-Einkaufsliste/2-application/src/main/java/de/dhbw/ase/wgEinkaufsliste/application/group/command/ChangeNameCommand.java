package de.dhbw.ase.wgEinkaufsliste.application.group.command;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;

public record ChangeNameCommand(GroupId groupId, String newName) { }
