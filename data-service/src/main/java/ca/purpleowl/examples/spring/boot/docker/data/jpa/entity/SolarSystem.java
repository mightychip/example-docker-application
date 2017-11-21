package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base.AbstractAstralEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "\"solar_systems\"")
public class SolarSystem extends AbstractAstralEntity {
    @Column(name = "system_id", nullable = false)
    private Long systemId;

    @Column(name = "constellation_id")
    private Long constellationId;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "security_class")
    private String securityClass;

    @Column(name = "security_status")
    private Double securityStatus;

    @Column(name = "star_id")
    private Long starId;

    @OneToMany(mappedBy = "solarSystem")
    private List<Planet> planets;

    @OneToMany(mappedBy = "solarSystem")
    private List<Stargate> stargates;

    public Long getSystemId() {
        return systemId;
    }

    public SolarSystem setSystemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public Long getConstellationId() {
        return constellationId;
    }

    public SolarSystem setConstellationId(Long constellationId) {
        this.constellationId = constellationId;
        return this;
    }

    public String getSystemName() {
        return systemName;
    }

    public SolarSystem setSystemName(String systemName) {
        this.systemName = systemName;
        return this;
    }

    public String getSecurityClass() {
        return securityClass;
    }

    public SolarSystem setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
        return this;
    }

    public Double getSecurityStatus() {
        return securityStatus;
    }

    public SolarSystem setSecurityStatus(Double securityStatus) {
        this.securityStatus = securityStatus;
        return this;
    }

    public Long getStarId() {
        return starId;
    }

    public SolarSystem setStarId(Long starId) {
        this.starId = starId;
        return this;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public SolarSystem setPlanets(List<Planet> planets) {
        this.planets = planets;
        return this;
    }

    public List<Stargate> getStargates() {
        return stargates;
    }

    public SolarSystem setStargates(List<Stargate> stargates) {
        this.stargates = stargates;
        return this;
    }
}
