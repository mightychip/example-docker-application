package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }
}