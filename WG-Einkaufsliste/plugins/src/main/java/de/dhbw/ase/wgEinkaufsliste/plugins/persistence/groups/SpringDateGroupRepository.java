package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SpringDateGroupRepository extends MongoRepository<GroupEntity, String> {
}
