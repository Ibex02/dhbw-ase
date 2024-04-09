package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.users;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.UserRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoUserRepository extends MongoRepository<UserRecord, String> {

    @Query("{userName:'?0'}")
    public UserRecord findUserByName(String name);
}
