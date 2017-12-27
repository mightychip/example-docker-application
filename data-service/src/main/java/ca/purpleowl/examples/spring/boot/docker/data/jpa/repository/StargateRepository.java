package ca.purpleowl.examples.spring.boot.docker.data.jpa.repository;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Stargate;
import org.springframework.data.repository.CrudRepository;

public interface StargateRepository extends CrudRepository<Stargate, Long> {
}
