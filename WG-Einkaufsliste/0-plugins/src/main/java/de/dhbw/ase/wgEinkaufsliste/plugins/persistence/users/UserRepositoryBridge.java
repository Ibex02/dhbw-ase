package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.users;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user.UserRecordToUserMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user.UserToUserRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryBridge implements UserRepository {

    private final MongoUserRepository repository;
    private final UserToUserRecordMapper mapToRecord;
    private final UserRecordToUserMapper mapFromRecord;

    @Autowired
    public UserRepositoryBridge(MongoUserRepository repository, UserToUserRecordMapper mapToRecord, UserRecordToUserMapper mapFromRecord) {
        this.repository = repository;
        this.mapToRecord = mapToRecord;
        this.mapFromRecord = mapFromRecord;
    }

    @Override
    public Optional<User> findById(UserId id) {
        var record = repository.findById(id.value());
        return record.map(mapFromRecord);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var record = repository.findUserByEmail(email);
        return record.map(mapFromRecord);
    }

    @Override
    public User save(User user) {
        var record = mapToRecord.apply(user);
        var saved = repository.save(record);
        return mapFromRecord.apply(saved);
    }

    @Override
    public void deleteById(UserId id) {
        repository.deleteById(id.value());
    }
}