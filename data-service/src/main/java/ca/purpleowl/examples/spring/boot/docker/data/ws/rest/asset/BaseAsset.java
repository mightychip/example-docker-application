package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

public abstract class BaseAsset {
    private final Long id;

    public BaseAsset(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
