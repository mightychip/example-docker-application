package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * That's no moon... Just kidding, this is seriously a JSON Moon Asset.  This represents a Moon in EVE Online and all
 * its glory.  Soon™ it will also hold information about what moon goo is inside the crunchy shell.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoonAsset extends AbstractBaseAsset {
    private final String name;
    private final Long systemId;
    private final Long planetId;

    @JsonCreator
    public MoonAsset(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("systemId") Long systemId,
                     @JsonProperty("planetId") Long planetId) {
        super(id);
        this.name = name;
        this.systemId = systemId;
        this.planetId = planetId;
    }

    public String getName() {
        return name;
    }

    public Long getSystemId() {
        return systemId;
    }

    public Long getPlanetId() {
        return planetId;
    }
}
