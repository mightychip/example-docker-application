package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents the JSON model that we're passing back and forth... the REST Asset, if you will.
 *
 * We use a few annotations to help Jackson marshall and unmarshall the data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExampleAsset extends BaseAsset {
    private final String message;

    @JsonCreator
    public ExampleAsset(@JsonProperty("id") Long id,
                        @JsonProperty("message") String message) {
        super(id);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
