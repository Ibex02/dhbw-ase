package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGroupRepository extends MongoRepository<GroupRecord, String> {
}
