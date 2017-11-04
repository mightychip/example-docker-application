package ca.purpleowl.examples.spring.boot.docker.data.jpa.repository;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.SolarSystem;
import org.springframework.data.repository.CrudRepository;

public interface SolarSystemRepository extends CrudRepository<SolarSystem, Long> {
}
