package ca.purpleowl.examples.spring.boot.docker.data.jpa.repository;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Example;
import org.springframework.data.repository.CrudRepository;

public interface ExampleRepository extends CrudRepository<Example, Long> {
}
