package org.ditebogo.fullstackmvc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ditebogo.fullstackmvc.Entities.People;
import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends  JpaRepository<People, Long>{

    boolean existsById(Long id);

    Optional<People> findById(Long id);

    Optional<People> findByName(String name);

}
