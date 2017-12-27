package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base.AbstractAstralEntity;

import javax.persistence.*;

/**
 * Now, you're going to be looking at this and going, "What the heck, man?!  You inherit an ID, but your'e still making
 * an ID?"
 *
 * Since I'm scraping this data from the EVE Swagger API, I want to keep the actual System ID.  It makes it easier to
 * create the relationships without having to do much mapping, and it will make it easier for alerts and system stats
 * and all kinds of stuff later.  It also guarantees this API works with other applications.
 */
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
    //TODO Set this to nullable=false after you finish work on the API.
    @JoinColumn(name = "planet_id", nullable=true)
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
