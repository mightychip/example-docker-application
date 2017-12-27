package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Just establishing the basics here.  Each concrete class gets its own table.  For some reason, Single Table is the
 * default, which seems crazy to me.  At any rate, that's kind of dirty if you're using a database and want it to be
 * human-readable in any fashion.  Or clean...  I'm rambling.
 *
 * We also establish our auto-generated Primary Key.  Each table's PK will have the same name for convenience.
 */

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
