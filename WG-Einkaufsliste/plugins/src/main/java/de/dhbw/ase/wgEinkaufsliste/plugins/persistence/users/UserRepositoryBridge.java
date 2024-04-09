package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.users;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.UserRecord;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.UserRecordToUserMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.UserToUserRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.User;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryBridge implements UserRepository {

    private final MongoUserRepository repository;
    private final UserToUserRecordMapper mapToEntity;
    private final UserRecordToUserMapper mapFromEntity;

    @Autowired
    public UserRepositoryBridge(MongoUserRepository repository, UserToUserRecordMapper mapToEntity, UserRecordToUserMapper mapFromEntity) {
        this.repository = repository;
        this.mapToEntity = mapToEntity;
        this.mapFromEntity = mapFromEntity;
    }

    @Override
    public User findUserByEmail(String email) {
        UserRecord entity = repository.findUserByName(email);
        return mapFromEntity.apply(entity);
    }

    @Override
    public void save(User user) {
        UserRecord entity = mapToEntity.apply(user);
        repository.save(entity);
    }
}