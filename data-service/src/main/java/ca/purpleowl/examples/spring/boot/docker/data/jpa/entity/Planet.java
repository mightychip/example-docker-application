package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planets")
public class Planet extends AbstractAstralEntity {
    @Column
    private Long planetId;

    @Column
    private String planetName;

    @Column
    private Long systemId;

    @Column
    private Integer typeId;

    public Long getPlanetId() {
        return planetId;
    }

    public Planet setPlanetId(Long planetId) {
        this.planetId = planetId;
        return this;
    }

    public String getPlanetName() {
        return planetName;
    }

    public Planet setPlanetName(String planetName) {
        this.planetName = planetName;
        return this;
    }

    public Long getSystemId() {
        return systemId;
    }

    public Planet setSystemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Planet setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }
}
