package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset;

public abstract class AbstractBaseAsset {
    private final Long id;

    public AbstractBaseAsset(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
