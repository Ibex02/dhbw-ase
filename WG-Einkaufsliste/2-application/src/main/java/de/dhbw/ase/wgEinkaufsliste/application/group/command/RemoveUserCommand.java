package de.dhbw.ase.wgEinkaufsliste.application.group.command;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;

public record RemoveUserCommand(GroupId groupId, UserId userId) { }
