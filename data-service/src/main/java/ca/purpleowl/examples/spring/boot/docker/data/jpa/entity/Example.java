package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "example")
public class Example extends AbstractBaseEntity {

    @Column
    private String message;

    public String getMessage() {
        return message;
    }

    public Example setMessage(String message) {
        this.message = message;
        return this;
    }
}
