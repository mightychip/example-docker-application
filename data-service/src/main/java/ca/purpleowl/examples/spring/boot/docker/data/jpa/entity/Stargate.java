package ca.purpleowl.examples.spring.boot.docker.data.jpa.entity;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.base.AbstractAstralEntity;

import javax.persistence.*;

@Entity
@Table(name = "\"stargates\"")
public class Stargate extends AbstractAstralEntity {
    @Column(name = "stargate_id", nullable = false)
    private Long stargateId;

    @Column(name = "stargate_name")
    private String stargateName;

    @Column(name = "type_id")
    private Integer typeId;

    //TODO Should really link this to another solar system.
    @Column(name = "destination_system_id", nullable = false)
    private Long destinationSystemId;

    @ManyToOne
    @JoinColumn(name = "systemId", nullable = false)
    private SolarSystem solarSystem;

    public Long getStargateId() {
        return stargateId;
    }

    public Stargate setStargateId(Long stargateId) {
        this.stargateId = stargateId;
        return this;
    }

    public String getStargateName() {
        return stargateName;
    }

    public Stargate setStargateName(String stargateName) {
        this.stargateName = stargateName;
        return this;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Stargate setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    public Long getDestinationSystemId() {
        return destinationSystemId;
    }

    public Stargate setDestinationSystemId(Long destinationSystemId) {
        this.destinationSystemId = destinationSystemId;
        return this;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public Stargate setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
        return this;
    }
}
