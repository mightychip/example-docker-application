package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base.AbstractAstralEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"planets\"")
public class Planet extends AbstractAstralEntity {
    @Column(name = "planet_id", nullable = false)
    private Long planetId;

    @Column(name = "planet_name")
    private String planetName;

    @Column(name = "type_id")
    private Integer typeId;

    @OneToMany(mappedBy = "planet")
    private List<Moon> moons;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private SolarSystem solarSystem;

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

    public Integer getTypeId() {
        return typeId;
    }

    public Planet setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public Planet setMoons(List<Moon> moons) {
        this.moons = moons;
        return this;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public Planet setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
        return this;
    }
}
