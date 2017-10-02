package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

/**
 * This POJO Represents the JSON model that we're passing back and forth... the REST Asset, if you will.
 *
 * There's usually not much to these things, and that is most assuredly the case here.
 */
public class ExampleAsset {
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public ExampleAsset setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExampleAsset setMessage(String message) {
        this.message = message;
        return this;
    }
}
