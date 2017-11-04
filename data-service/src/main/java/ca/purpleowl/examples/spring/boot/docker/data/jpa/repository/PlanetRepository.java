package ca.purpleowl.examples.spring.boot.docker.data.jpa.repository;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Planet;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, Long> {
}
