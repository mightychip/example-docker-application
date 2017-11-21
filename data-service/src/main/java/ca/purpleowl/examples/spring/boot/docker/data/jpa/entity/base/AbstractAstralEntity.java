package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.embeddable.AstralPosition;

import javax.persistence.Embedded;
import javax.persistence.Entity;

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
