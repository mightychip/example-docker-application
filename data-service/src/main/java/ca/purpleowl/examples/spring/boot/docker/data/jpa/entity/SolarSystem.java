package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "solar_systems")
public class SolarSystem extends AbstractAstralEntity {
    @Column
    private Long systemId;

    @Column
    private Long constellationId;

    @Column
    private String systemName;

    @Column
    private String securityClass;

    @Column
    private Double securityStatus;

    @Column
    private Long starId;

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
}
