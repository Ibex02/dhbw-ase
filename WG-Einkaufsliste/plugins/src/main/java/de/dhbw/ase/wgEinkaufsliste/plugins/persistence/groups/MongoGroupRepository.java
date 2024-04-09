package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGroupRepository extends MongoRepository<GroupRecord, String> {
}
