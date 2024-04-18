package de.dhbw.ase.wgEinkaufsliste.application.group.event;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import org.springframework.context.ApplicationEvent;

public class GroupCreatedEvent extends ApplicationEvent {

    private final Group group;

    public GroupCreatedEvent(Group group, Object source) {
        super(source);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
