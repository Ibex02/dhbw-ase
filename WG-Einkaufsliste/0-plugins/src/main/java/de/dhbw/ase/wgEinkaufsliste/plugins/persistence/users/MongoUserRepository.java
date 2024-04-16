package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.users;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user.UserRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<UserRecord, String> {

    @Query("{email:'?0'}")
    Optional<UserRecord> findUserByEmail(String email);
}
