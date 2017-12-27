package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.embeddable.AstralPosition;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * A lot of things (probably everything at the time of writing this) have an {@link AstralPosition}.  This parent class
 * helps avoid that boilerplate.  Each AbstractAstralEntity is guaranteed to have an ID and the other qualities of
 * {@link AbstractBaseEntity}, so I extend that.
 */
@Entity
public abstract class AbstractAstralEntity extends AbstractBaseEntity {
    @Embedded
    private AstralPosition position;

    public AstralPosition getPosition() {
        return position;
    }

    public AbstractAstralEntity setPosition(AstralPosition position) {
        this.position = position;
        return this;
    }
}
