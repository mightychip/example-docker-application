package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

/**
 * This POJO Represents the JSON model that we're passing back and forth... the REST Asset, if you will.
 *
 * There's usually not much to these things, and that is most assuredly the case here.
 */
public class ExampleAsset {
    private final Long id;
    private final String message;

    public ExampleAsset(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
