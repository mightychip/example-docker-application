package ca.purpleowl.examples.spring.boot.docker.data.jpa.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AstralPosition {
    @Column(name = "x")
    private Long xPosition;

    @Column(name = "y")
    private Long yPosition;

    @Column(name = "z")
    private Long zPosition;

    public Long getxPosition() {
        return xPosition;
    }

    public AstralPosition setxPosition(Long xPosition) {
        this.xPosition = xPosition;
        return this;
    }

    public Long getyPosition() {
        return yPosition;
    }

    public AstralPosition setyPosition(Long yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    public Long getzPosition() {
        return zPosition;
    }

    public AstralPosition setzPosition(Long zPosition) {
        this.zPosition = zPosition;
        return this;
    }
}
