package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base.AbstractAstralEntity;

import javax.persistence.*;

@Entity
@Table(name = "\"moons\"")
public class Moon extends AbstractAstralEntity {
    @Column(name = "moon_id", nullable = false)
    private Long moonId;

    @Column(name = "moon_name")
    private String moonName;

    @Column(name = "system_id", nullable = false)
    private Long systemId;

    //You won't see this relationship in the Swagger API, and I have no idea why.
    @ManyToOne
    @JoinColumn(name = "planet_id", nullable=false)
    private Planet planet;

    public Long getMoonId() {
        return moonId;
    }

    public Moon setMoonId(Long moonId) {
        this.moonId = moonId;
        return this;
    }

    public String getMoonName() {
        return moonName;
    }

    public Moon setMoonName(String moonName) {
        this.moonName = moonName;
        return this;
    }

    public Long getSystemId() {
        return systemId;
    }

    public Moon setSystemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public Planet getPlanet() {
        return planet;
    }

    public Moon setPlanet(Planet planet) {
        this.planet = planet;
        return this;
    }
}
