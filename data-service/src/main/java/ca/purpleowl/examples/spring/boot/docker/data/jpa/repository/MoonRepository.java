package ca.purpleowl.examples.spring.boot.docker.data.jpa.repository;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Moon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MoonRepository extends CrudRepository<Moon, Long> {
    Optional<Moon> findByMoonId(Long moonId);
    boolean existsByMoonId(Long moonId);
}
