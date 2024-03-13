package de.dhbw.ase.wgEinkaufsliste.plugins.persistence;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface springDataForBridge extends JpaRepository<EntityExample, String> {

}
