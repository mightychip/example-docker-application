package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    public Long getId() {
        return id;
    }

    public AbstractBaseEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
