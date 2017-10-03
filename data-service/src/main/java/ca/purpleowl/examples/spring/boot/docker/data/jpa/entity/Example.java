package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "example")
public class Example extends BaseEntity {

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
