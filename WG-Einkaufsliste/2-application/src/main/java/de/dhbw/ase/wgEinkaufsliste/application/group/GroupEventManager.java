package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.event.GroupCreatedEvent;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.GroupDeletedEvent;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.UserAddedEvent;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.UserRemovedEvent;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class GroupEventManager {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public GroupEventManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void raiseGroupCreated(Group group, Object source) {
        var event = new GroupCreatedEvent(group, source);
        applicationEventPublisher.publishEvent(event);
    }

    public void raiseGroupDeleted(Group group, Object source) {
        var event = new GroupDeletedEvent(group, source);
        applicationEventPublisher.publishEvent(event);
    }

    public void raiseUserAdded(Group group, User user, Object source) {
        var event = new UserAddedEvent(group, user, source);
        applicationEventPublisher.publishEvent(event);
    }

    public void raiseUserRemoved(Group group, User user, Object source) {
        var event = new UserRemovedEvent(group, user, source);
        applicationEventPublisher.publishEvent(event);
    }
}