package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.users;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user.UserRecordToUserMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user.UserToUserRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public User findById(String id) {
        var entity = repository.findById(id);
        return entity.map(mapFromRecord).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        var entity = repository.findUserByName(email);
        return mapFromRecord.apply(entity);
    }

    @Override
    public void save(User user) {
        var entity = mapToRecord.apply(user);
        repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
